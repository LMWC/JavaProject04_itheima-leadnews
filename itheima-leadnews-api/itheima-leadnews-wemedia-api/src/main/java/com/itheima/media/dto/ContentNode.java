package com.itheima.media.dto;

import lombok.Data;

@Data

public class ContentNode {
    //type 指定类型  text 标识文本   image 标识 图片
    private String type;
    //value 指定内容
    private String value;

}