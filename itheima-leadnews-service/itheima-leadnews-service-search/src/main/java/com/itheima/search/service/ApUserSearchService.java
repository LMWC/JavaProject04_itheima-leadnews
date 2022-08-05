package com.itheima.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.search.pojo.ApUserSearch;

import java.util.Set;

/**
 * <p>
 * APP用户搜索信息表 服务类
 * </p>
 *
 * @author ljh
 * @since 2022-08-04
 */
public interface ApUserSearchService extends IService<ApUserSearch> {


    //添加数据到redis中存储搜索记录的
    void addUserSearch(String eqId,String keyword);
    Set<String> findByEqId(Integer eqId);
}
