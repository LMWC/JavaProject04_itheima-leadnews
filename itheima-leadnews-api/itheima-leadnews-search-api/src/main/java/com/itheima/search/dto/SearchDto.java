package com.itheima.search.dto;

import lombok.Data;

@Data
public class SearchDto {

    // 设备ID
    Integer equipmentId;
    /**
     * 搜索关键字
     */
    String keywords;
    /**
     * 当前页
     */
    Integer page;
    /**
     * 分页条数
     */
    Integer size;


}