package com.itheima.search.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.search.mapper.ApUserSearchMapper;
import com.itheima.search.pojo.ApUserSearch;
import com.itheima.search.service.ApUserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * APP用户搜索信息表 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2022-08-04
 */
@Service
public class ApUserSearchServiceImpl extends ServiceImpl<ApUserSearchMapper, ApUserSearch> implements ApUserSearchService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    @Async
    public void addUserSearch(String eqId, String keyword) {
        //逻辑： 添加某一个用户的搜索记录 到redis中 存5条
        // key  value      key:eqId  value: set

        //规则 需要专门写一个工具类去产生： 规则： key:    [公司名]:[项目名]:[业务ID]:[16进制4位]:[功能ID]：key
        stringRedisTemplate.opsForSet().add("USER_SEARCH_:"+eqId,keyword);
        stringRedisTemplate.expire("USER_SEARCH_:"+eqId,7, TimeUnit.DAYS);
    }

    @Override
    public Set<String> findByEqId(Integer eqId) {
        Set<String> members = stringRedisTemplate.opsForSet().members("USER_SEARCH_:" + eqId);

        if (members!=null&&members.size()>5) {
            //members.stream().map()
        }
        return members;
    }
}
