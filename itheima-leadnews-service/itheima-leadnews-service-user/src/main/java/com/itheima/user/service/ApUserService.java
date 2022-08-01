package com.itheima.user.service;

import com.itheima.common.exception.LeadNewsException;
import com.itheima.common.util.au.TokenJsonVo;
import com.itheima.user.dto.LoginDto;
import com.itheima.user.pojo.ApUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * APP用户信息表 服务类
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
public interface ApUserService extends IService<ApUser> {


    TokenJsonVo login(LoginDto loginDto) throws LeadNewsException;
}
