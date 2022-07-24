package com.itheima.user.service.impl;

import com.itheima.user.pojo.ApUser;
import com.itheima.user.mapper.ApUserMapper;
import com.itheima.user.service.ApUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * APP用户信息表 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
@Service
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {

}
