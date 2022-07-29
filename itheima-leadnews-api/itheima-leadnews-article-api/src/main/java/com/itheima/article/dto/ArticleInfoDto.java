package com.itheima.article.dto;

import com.itheima.article.pojo.ApArticle;
import com.itheima.article.pojo.ApArticleConfig;
import com.itheima.article.pojo.ApArticleContent;
import lombok.Data;

//最好 不用数据库相关的POJO 最好自己件
@Data
public class ArticleInfoDto {
    private ApArticle apArticle;
    private ApArticleContent apArticleContent;
    private ApArticleConfig apArticleConfig;
}
