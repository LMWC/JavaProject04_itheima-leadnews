package com.itheima.article.vo;

import lombok.Data;

@Data
public class ArticleRedisVo {

    private Long id;

    private String title;

    private Integer authorId;

    private String authorName;

    private Integer channelId;

    private String channelName;

    private Integer layout;

    private Integer flag;

    private String images;

    private String labels;
}