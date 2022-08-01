package com.itheima.user.service;

import com.itheima.common.exception.LeadNewsException;
import com.itheima.user.dto.UserRelationDto;
import com.itheima.user.pojo.ApUserFollow;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * APP用户关注信息表 服务类
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
public interface ApUserFollowService extends IService<ApUserFollow> {


    void follow(UserRelationDto userRelationDto) throws LeadNewsException;
}
