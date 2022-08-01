package com.itheima.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.article.dto.ArticleInfoDto;
import com.itheima.article.mapper.ApArticleConfigMapper;
import com.itheima.article.mapper.ApArticleContentMapper;
import com.itheima.article.pojo.ApArticle;
import com.itheima.article.mapper.ApArticleMapper;
import com.itheima.article.pojo.ApArticleConfig;
import com.itheima.article.pojo.ApArticleContent;
import com.itheima.article.service.ApArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.common.pojo.PageInfo;
import com.itheima.common.pojo.PageRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 文章信息表，存储已发布的文章 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
@Service
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {
    @Autowired
    private ApArticleMapper apArticleMapper;

    @Autowired
    private ApArticleConfigMapper apArticleConfigMapper;

    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

    @Override
    @Transactional
    public ApArticle saveArticle(ArticleInfoDto dto) {
        //插入3个表即可
        ApArticle apArticle = dto.getApArticle();
        Long id1 = apArticle.getId();
        System.out.println(id1);
        apArticleMapper.insert(apArticle);
        Long id = apArticle.getId();
        System.out.println(id);

        ApArticleConfig apArticleConfig = dto.getApArticleConfig();
        apArticleConfig.setArticleId(id);
        apArticleConfigMapper.insert(apArticleConfig);

        ApArticleContent apArticleContent = dto.getApArticleContent();
        apArticleContent.setArticleId(id);
        apArticleContentMapper.insert(apArticleContent);

        return apArticle;
    }

    @Override
    public PageInfo<ApArticle> pageByOrder(PageRequestDto<ApArticle> pageRequestDto) {


        /**
         *
         *
         select apa.* from ap_article apa left join ap_article_config aac on apa.id = aac.article_id
         where  aac.is_delete=0 and aac.is_down=0 and apa.channel_id=2 order by publish_time desc limit 0,10;
         */

        //1.获取当前的页码和每页显示的行
        Long page = pageRequestDto.getPage();
        Long size = pageRequestDto.getSize();
        //2.获取请求题对象 获取到频道的ID
        ApArticle body = pageRequestDto.getBody();
        Integer channelId=0;//如果是0 标识没有频道 >0 才是频道
        if(body!=null && body.getChannelId()!=null){
            channelId =  body.getChannelId();
        }
        Long start = (page-1)*size;
        //3.执行自己写的分页查询获取到当前的页的记录集合
        List<ApArticle> list = apArticleMapper.pageByOrder(channelId,start,size);

        //4.执行获取总记录数
        Long total = apArticleMapper.selectArticleCount(channelId);
        //5.计算总页数  等 封装对象返回
        Long totalPages= total%size>0?total/size+1:total/size;

        return new PageInfo<ApArticle>(
                page,
                size,
                total,
                totalPages,
                list
        );
    }

    //一个SQL就搞定 定义POJO 页面需要什么字段 就定义什么字段 执行SQL语句  SQL语句
    //
    @Override
    public ArticleInfoDto detailByArticleId(Long articleId) {
        //1.根据ID 获取文章的表数据
        ApArticle apArticle = apArticleMapper.selectById(articleId);
        //2.根据ID 获取文章的配置表的数据
        QueryWrapper<ApArticleConfig> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("article_id",articleId);
        queryWrapper1.eq("is_delete",0);
        queryWrapper1.eq("is_down",0);
        ApArticleConfig apArticleConfig = apArticleConfigMapper.selectOne(queryWrapper1);
        if(apArticleConfig==null){
            return null;
        }
        //3.根据ID 获取文章内容表的数据
        QueryWrapper<ApArticleContent> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("article_id",articleId);
        ApArticleContent content = apArticleContentMapper.selectOne(queryWrapper2);
        //4.封装 返回
        ArticleInfoDto articleInfoDto = new ArticleInfoDto();
        articleInfoDto.setApArticle(apArticle);
        articleInfoDto.setApArticleConfig(apArticleConfig);
        articleInfoDto.setApArticleContent(content);
        return articleInfoDto;
    }
}
