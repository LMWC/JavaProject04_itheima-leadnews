package com.itheima.core.controller;


import com.itheima.common.pojo.Result;

import java.io.Serializable;
import java.util.List;

/***
 * 描述
 * @author ljh
 * @packagename com.changgou.core
 * @version 1.0
 * @date 2020/8/10
 */
public interface ISelectController<T> {
    //根据ID 获取信息
    public Result<T> findById(Serializable id);

    //根据ID 获取信息列表
    public Result<List<T>> findAll();

    //根据条件查询 select * from xx  where column1=页面传递过来的值 and column2=页面传递过来的值
    public Result<List<T>> findByRecord(T record);

}
