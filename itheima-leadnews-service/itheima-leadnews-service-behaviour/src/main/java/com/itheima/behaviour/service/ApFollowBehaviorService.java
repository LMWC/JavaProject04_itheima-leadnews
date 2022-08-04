package com.itheima.behaviour.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.behaviour.dto.FollowBehaviorDto;
import com.itheima.behaviour.pojo.ApFollowBehavior;

/**
 * <p>
 * APP关注行为表 服务类
 * </p>
 *
 * @author ljh
 * @since 2022-08-03
 */
public interface ApFollowBehaviorService extends IService<ApFollowBehavior> {


    void saveFollowBehavior(FollowBehaviorDto followBehaviorDto);
}
