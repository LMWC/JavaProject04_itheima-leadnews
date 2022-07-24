package com.itheima.core.controller;


import com.itheima.common.pojo.Result;

import java.io.Serializable;

/***
 * 描述
 * @author ljh
 * @packagename com.changgou.core
 * @version 1.0
 * @date 2020/8/10
 */
public interface IDeleteController<T> {
    /**
     * 根据ID 删除
     *
     * @param id
     * @return
     */
    Result deleteById(Serializable id);
}
