package com.itheima.behaviour.dto;

import lombok.Data;

@Data
public class LikesBehaviourDto {
    // 设备ID
    Integer equipmentId;

    // 文章、动态、评论等ID
    Long articleId;
    /**
     * 喜欢内容类型
     * 0文章
     * 1动态
     *
     */
    Integer type;

    /**
     * 喜欢操作方式
     * 1 点赞
     * 0 取消点赞
     */
    Integer operation;
}