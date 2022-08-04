package com.itheima.behaviour.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.behaviour.dto.LikesBehaviourDto;
import com.itheima.behaviour.pojo.ApLikesBehavior;
import com.itheima.common.exception.LeadNewsException;

/**
 * <p>
 * APP点赞行为表 服务类
 * </p>
 *
 * @author ljh
 * @since 2022-08-03
 */
public interface ApLikesBehaviorService extends IService<ApLikesBehavior> {


    void like(LikesBehaviourDto likesBehaviourDto) throws LeadNewsException;
}
