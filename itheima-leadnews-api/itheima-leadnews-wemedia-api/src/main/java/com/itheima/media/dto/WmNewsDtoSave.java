package com.itheima.media.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data

public class WmNewsDtoSave {
    //主键ID
    private Integer id;

    //文章标题
    private String title;

    //图文内容
    private List<ContentNode> content;

    //指定为封面类型  0 是无图  1 是单图  3 是多图  -1 是自动
    private Integer type;

    //指定选中的频道ID
    private Integer channelId;

    //指定标签
    private String labels;

    //状态 0 草稿  1 提交 待审核 （该字段可以不用设置,前端不必传递）
    private Integer status;

    //定时发布时间
    private LocalDateTime publishTime;

    //封面图片
    private List<String> images;
}