package com.itheima.media.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.common.pojo.PageInfo;
import com.itheima.common.pojo.PageRequestDto;
import com.itheima.common.util.RequestContextUtil;
import com.itheima.media.dto.ContentNode;
import com.itheima.media.dto.WmNewsDto;
import com.itheima.media.dto.WmNewsDtoSave;
import com.itheima.media.mapper.WmUserMapper;
import com.itheima.media.pojo.WmNews;
import com.itheima.media.mapper.WmNewsMapper;
import com.itheima.media.pojo.WmUser;
import com.itheima.media.service.WmNewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 自媒体图文内容信息表 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
@Service
public class WmNewsServiceImpl extends ServiceImpl<WmNewsMapper, WmNews> implements WmNewsService {

    @Autowired
    private WmNewsMapper wmNewsMapper;


    @Override
    public PageInfo<WmNews> findByPageDto(PageRequestDto<WmNewsDto> pageRequestDto) {
        //select * from wm_news where status=? and title like '%?%' and channle_id=? and
        // publish_time between ? and ? and user_id=当前登录的用户的ID  limit 0,10

        //1. 获取当前页码和每页显示的行
        Long page = pageRequestDto.getPage();
        Long size = pageRequestDto.getSize();

        //2. 获取到页面传递过来的请求体对象（查询的条件对象）
        WmNewsDto body = pageRequestDto.getBody();

        //3.判断条件 并 拼接查询条件对象
        QueryWrapper<WmNews> queryCondition = new QueryWrapper<>();
        Integer userId = RequestContextUtil.getUserId();
        queryCondition.eq("user_id", userId);


        if (body != null) {

            if (body.getStatus() != null) {
                queryCondition.eq("status", body.getStatus());
            }
            if (!StringUtils.isEmpty(body.getTitle())) {
                queryCondition.like("title", body.getTitle());
            }
            if (body.getChannelId() != null) {
                queryCondition.eq("channel_id", body.getChannelId());
            }
            if (body.getStartTime() != null && body.getEndTime() != null) {
                queryCondition.between("publish_time", body.getStartTime(), body.getEndTime());
            }
        }
        IPage<WmNews> pageCondition = new Page<>(page, size);

        //4.执行分页查询

        IPage<WmNews> pageResult = wmNewsMapper.selectPage(pageCondition, queryCondition);

        //5.获取结果 封装 返回

        return new PageInfo<WmNews>(
                pageResult.getCurrent(),
                pageResult.getSize(),
                pageResult.getTotal(),
                pageResult.getPages(),
                pageResult.getRecords());
    }

    @Override
    public void save(WmNewsDtoSave wmNewsDtoSave, Integer isSubmit) {
        //这里实现保存草稿和提交审核的代码

        //1.将页面的POJO数据 设置到数据库对应的POJO中
        WmNews wmNews = new WmNews();
        BeanUtils.copyProperties(wmNewsDtoSave, wmNews);
        //2.补充其他的属性值
        //2.1 设置封面的类型 0,1,3 如果页面传递过来的是-1 需要判断
        if (wmNewsDtoSave.getType() == -1) {
            //重新设置type的值,需要从content中判断 如果有0个图则0  如果有1个图 则1 如果超过一个 则3
            List<String> imagesList = getImages(wmNewsDtoSave.getContent());
            if (imagesList.size() == 0) {
                wmNews.setType(0);
            } else if (imagesList.size() == 1) {
                wmNews.setType(1);
            } else {
                wmNews.setType(3);
            }

            //重新设置图片地址，不是从页面来的 是从内容来的

            wmNews.setImages(String.join(",", imagesList));
        } else {
            //2.2 设置图片的路径 逗号分隔的
            List<String> images = wmNewsDtoSave.getImages();

            //页面传递过来了（一定不能选择 自动）
            if(images!=null && images.size()>0) {
                wmNews.setImages(String.join(",", images));
            }
        }
        //2.3 设置内容 数据库存储的是JSON字符串
        List<ContentNode> content = wmNewsDtoSave.getContent();
        wmNews.setContent(JSON.toJSONString(content));

        //2.4 设置userId 当前登录的用户的ID
        wmNews.setUserId(RequestContextUtil.getUserId());

        //2.5 设置创建时间（只有insert的时候才会添加这个时间）
        if(wmNewsDtoSave.getId()==null){
            wmNews.setCreatedTime(LocalDateTime.now());
        }
        //2.6设置提交的时间
        if(isSubmit==1) {
            wmNews.setSubmitedTime(LocalDateTime.now());
        }
        //2.7 设置状态
        wmNews.setStatus(isSubmit);

        //设置默认下架
        wmNews.setEnable(0);

        //3.执行insert/update
        if(wmNewsDtoSave.getId()==null){
            wmNewsMapper.insert(wmNews);
        }else{
            wmNewsMapper.updateById(wmNews);
        }


    }

    @Override
    public WmNewsDtoSave getDtoById(Integer id) {
        WmNews wmNews = wmNewsMapper.selectById(id);

        if(wmNews!=null){
            WmNewsDtoSave wmNewsDtoSave = new WmNewsDtoSave();
            BeanUtils.copyProperties(wmNews,wmNewsDtoSave);
            //设置内容
            String content = wmNews.getContent();
            List<ContentNode> contentNodes = JSON.parseArray(content, ContentNode.class);
            wmNewsDtoSave.setContent(contentNodes);
            //设置图片
            String images = wmNews.getImages();
            if(!StringUtils.isEmpty(images)){
                //设置图片列表
                wmNewsDtoSave.setImages(Arrays.asList(images.split(",")));
            }
            return wmNewsDtoSave;
        }
        return null;
    }

    private List<String> getImages(List<ContentNode> nodes) {
        List<String> images = new ArrayList<>();
        for (ContentNode node : nodes) {
            if (node.getType().equals("image")) {
                images.add(node.getValue());
            }
        }
        return images;
    }
}
