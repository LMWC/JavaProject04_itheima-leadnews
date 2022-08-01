package com.itheima.article.service;

import com.itheima.article.dto.ArticleInfoDto;
import com.itheima.article.pojo.ApArticle;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.common.pojo.PageInfo;
import com.itheima.common.pojo.PageRequestDto;

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
}
