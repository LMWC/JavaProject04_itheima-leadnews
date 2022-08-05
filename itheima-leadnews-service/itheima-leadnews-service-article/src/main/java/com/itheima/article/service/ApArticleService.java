package com.itheima.article.service;

import com.itheima.article.dto.ArticleBehaviourDtoQuery;
import com.itheima.article.dto.ArticleInfoDto;
import com.itheima.article.pojo.ApArticle;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.common.exception.LeadNewsException;
import com.itheima.common.pojo.PageInfo;
import com.itheima.common.pojo.PageRequestDto;

import java.util.Map;

/**
 * <p>
 * 文章信息表，存储已发布的文章 服务类
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
public interface ApArticleService extends IService<ApArticle> {


    ApArticle saveArticle(ArticleInfoDto dto);


    PageInfo<ApArticle> pageByOrder(PageRequestDto<ApArticle> pageRequestDto);


    ArticleInfoDto detailByArticleId(Long articleId);


    Map<String, Object> loadArticleBehaviour(ArticleBehaviourDtoQuery dtoQuery) throws LeadNewsException;

    /**
     * 业务逻辑方法
     */
    void saveToRedis();
}
