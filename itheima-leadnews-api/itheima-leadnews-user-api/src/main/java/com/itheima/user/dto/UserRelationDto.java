package com.itheima.user.dto;

import lombok.Data;

@Data
public class UserRelationDto {

    // 文章作者ID
    Integer authorId;

    //作者名称
    String authorName;

    // 文章id
    Long articleId;
    /**
     * 操作方式
     * 1  关注
     * 0  取消
     */
    Integer operation;
}