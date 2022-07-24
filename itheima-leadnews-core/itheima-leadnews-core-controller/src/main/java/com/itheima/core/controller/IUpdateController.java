package com.itheima.core.controller;


import com.itheima.common.pojo.Result;

/***
 * 描述
 * @author ljh
 * @packagename
 * @version 1.0
 * @date 2020/8/9
 */
public interface IUpdateController<T> {

    /**
     * 根据对象进行更新 根据ID
     *
     * @param record
     * @return
     */
    Result updateByPrimaryKey(T record);
}
