package com.itheima.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.itheima.admin.mapper.AdChannelMapper;
import com.itheima.admin.mapper.AdSensitiveMapper;
import com.itheima.admin.pojo.AdChannel;
import com.itheima.admin.pojo.AdSensitive;
import com.itheima.admin.service.WemediaNewsAutoScanService;
import com.itheima.article.dto.ArticleInfoDto;
import com.itheima.article.feign.ApArticleFeign;
import com.itheima.article.feign.ApAuthorFeign;
import com.itheima.article.pojo.ApArticle;
import com.itheima.article.pojo.ApArticleConfig;
import com.itheima.article.pojo.ApArticleContent;
import com.itheima.article.pojo.ApAuthor;
import com.itheima.common.constants.BusinessConstants;
import com.itheima.common.pojo.Result;
import com.itheima.common.util.GreenImageScan;
import com.itheima.common.util.GreenTextScan;
import com.itheima.common.util.SensitiveWordUtil;
import com.itheima.dfs.feign.DfsFeign;
import com.itheima.media.dto.ContentNode;
import com.itheima.media.feign.WmNewsFeign;
import com.itheima.media.pojo.WmNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WemediaNewsAutoScanServiceImpl implements WemediaNewsAutoScanService {
    @Autowired
    private WmNewsFeign wmNewsFeign;

    @Autowired
    private ApArticleFeign apArticleFeign;

    @Autowired
    private ApAuthorFeign apAuthorFeign;

    @Autowired
    private AdChannelMapper adChannelMapper;

    @Override
    public void autoScanByMediaNewsId(Integer id) throws Exception {
        // 2.1 获取到消息内容 通过Feign调用获取自媒体文章信息
        Result<WmNews> result = wmNewsFeign.findById(id);
        WmNews wmNews = result.getData();
        if (wmNews != null) {
            // 2.2 获取文章的标题 和 内容中解析出来的文本
            List<String> texts = getTextFromContent(wmNews.getContent(), wmNews.getTitle());

            // 2.3 获取到文章的封面图片和内容中解析出来的图片
            List<String> images = getImagesFromContent(wmNews.getContent(), wmNews.getImages());
            // 2.4 调用获取阿里云反垃圾服务进行审核文本 和 审核图片 以及调用管理微服务本身的敏感词审核
            String status = scanTextAndImage(texts, images);
            boolean flag = false;
            // 2.5 判断审核的结果
            switch (status) {
                case BusinessConstants.ScanConstants.BLOCK: {
                    // 2.5.1 如果是Block  则 通过feign调用更新自媒体文章的状态为2
                    WmNews record = new WmNews();
                    record.setId(wmNews.getId());
                    record.setStatus(2);
                    wmNewsFeign.updateByPrimaryKey(record);//update xxx set status=2 where id=?
                    break;
                }
                case BusinessConstants.ScanConstants.REVIEW: {
                    // 2.5.2 如果是review 则 通过feign调用更新自媒体文章的状态为3
                    WmNews record = new WmNews();
                    record.setId(wmNews.getId());
                    record.setStatus(3);
                    wmNewsFeign.updateByPrimaryKey(record);//update xxx set status=3 where id=?
                    break;
                }
                case BusinessConstants.ScanConstants.PASS: {
                    WmNews record = new WmNews();
                    record.setId(wmNews.getId());
                    // 2.5.3 如果是pass
                    if (wmNews.getPublishTime() != null) {
                        // 2.5.3.1 判断发布时间是否有值 如果有  则通过feign调用更新自媒体文章的状态为8
                        record.setStatus(8);
                    } else {
                        // 2.5.3.2 判断发布时间是否有值 如果无  则通过feign调用更新自媒体文章的状态为9
                        record.setStatus(9);
                        flag = true;
                    }
                    wmNewsFeign.updateByPrimaryKey(record);
                    break;
                }
                default: {
                    System.out.println("有问题");
                    break;
                }
            }
            if (flag) {
                createArticleInfoData(wmNews);
            }
        }
    }

    @Override
    public void createArticleInfoData(WmNews wmNews){
        // 2.6 保存文章信息到 article库中  需要保存3张表的数据
        ArticleInfoDto dto = new ArticleInfoDto();

        //2.6.1 设置文章对象
        ApArticle article = new ApArticle();
        article.setTitle(wmNews.getTitle());
        //authorid
        ApAuthor apAuthor = apAuthorFeign.getByWmUserId(wmNews.getUserId());
        if(apAuthor!=null) {
            article.setAuthorId(apAuthor.getId());
            article.setAuthorName(apAuthor.getName());
        }

        article.setChannelId(wmNews.getChannelId());
        AdChannel adChannel = adChannelMapper.selectById(wmNews.getChannelId());
        article.setChannelName(adChannel.getName());


        article.setLayout(wmNews.getType());
        article.setFlag(0);//普通

        article.setImages(wmNews.getImages());
        article.setLabels(wmNews.getLabels());
        article.setCreatedTime(LocalDateTime.now());
        article.setPublishTime(LocalDateTime.now());
        dto.setApArticle(article);
        //2.6.2 设置文章配置对象
        ApArticleConfig articleConfig = new ApArticleConfig();
        articleConfig.setIsComment(1);
        articleConfig.setIsForward(1);
        articleConfig.setIsDown(0);//没有下架
        articleConfig.setIsDelete(0);//没有删除 逻辑删除

        dto.setApArticleConfig(articleConfig);
        //2.6.3 设置文章内容对象
        ApArticleContent articleContent = new ApArticleContent();
        articleContent.setContent(wmNews.getContent());
        dto.setApArticleContent(articleContent);

        Result<ApArticle> resultArticle = apArticleFeign.save(dto);
        ApArticle datafromArticle = resultArticle.getData();
        Long articleId = datafromArticle.getId();
        WmNews record = new WmNews();
        record.setId(wmNews.getId());
        record.setArticleId(articleId);
        // 2.7 调用feign更新文章的ID 到自媒体文章表中
        wmNewsFeign.updateByPrimaryKey(record);//update wm_news set article_id=? where id=?

    }


    @Autowired
    private GreenTextScan greenTextScan;

    @Autowired
    private GreenImageScan greenImageScan;

    @Autowired
    private DfsFeign dfsFeign;

    @Autowired
    private AdSensitiveMapper adSensitiveMapper;

    /**
     * @param texts  待审核的文本
     * @param images 待审核的图片 ["url1","url2"]  192.168.211.136
     * @return String  3个值  pass review block
     * @throws Exception
     */
    private String scanTextAndImage(List<String> texts, List<String> images) throws Exception {

        //1.阿里云 审核文本  map  key: suggestion value : pass review block
        Map map = greenTextScan.greeTextScan(texts);
        String suggestion = map.get("suggestion").toString();
        if (!suggestion.equals(BusinessConstants.ScanConstants.PASS)) {
            return suggestion;
        }

        //2.阿里云 审核图片 [byte[],byte[],byte[]]
        //先远程调用feign 从dfs微服务中获取到图片路径 对应的图片字节数组--》1.声明feign接口 2 dfs微服务编写controller接受请求 3.当前微服务添加依赖 4 开启feignclients 5 注入
        List<byte[]> bytes = dfsFeign.downLoad(images);
        //再调用这个方法 实现阿里云的审核
        Map map1 = greenImageScan.imageScan(bytes);
        String suggestion1 = map1.get("suggestion").toString();
        if (!suggestion1.equals(BusinessConstants.ScanConstants.PASS)) {
            return suggestion1;
        }

        //系统启动的时候 初始化这颗树 然后这里只要用他判断即可不需要再进行查询初始化，还要同步 todo


        //3.自己审核敏感词
        //从数据库查询所有的敏感词 初始化一颗树


        /*Map tree = redisTemplate.find();
        if(tree){
            //直接使用 tree进行查询
        }else{
            //从数据库查到了之后 再设置到redis
            redisTempalte.insert(tree);
        }*/

        List<String> adSensitives = adSensitiveMapper.selectAdSensitive();
        SensitiveWordUtil.initMap(adSensitives);
        //查询文章的内容是否在这棵树里面存在 如果存在 有敏感词 返回BLOCK
        for (String text : texts) {
            Map<String, Integer> stringIntegerMap = SensitiveWordUtil.matchWords(text);
            if (stringIntegerMap.size() > 0) {
                return BusinessConstants.ScanConstants.BLOCK;
            }
        }


        //4.返回状态
        return BusinessConstants.ScanConstants.PASS;
    }

    /**
     * @param content "[{type:"text",value:"xxxxxx"},{type:"image",value:"http://192.168.211.136/group1/m00/000"}]"
     *                List<POJO>
     *                POJO{type,value}
     * @param images  "url1"
     * @return
     */
    private List<String> getImagesFromContent(String content, String images) {
        List<String> list = new ArrayList<>();
        if (!StringUtils.isEmpty(images)) {
            String[] split = images.split(",");//  ["url1"]
            List<String> urls = Arrays.asList(split);
            list.addAll(urls);
        }

        List<ContentNode> contentNodes = JSON.parseArray(content, ContentNode.class);
        for (ContentNode contentNode : contentNodes) {
            if (contentNode.getType().equals("image")) {
                list.add(contentNode.getValue());
            }
        }
        return list;
    }

    private List<String> getTextFromContent(String content, String title) {
        List<String> list = new ArrayList<>();
        list.add(title);
        List<ContentNode> contentNodes = JSON.parseArray(content, ContentNode.class);
        for (ContentNode contentNode : contentNodes) {
            if (contentNode.getType().equals("text")) {
                list.add(contentNode.getValue());
            }
        }
        return list;
    }
}
