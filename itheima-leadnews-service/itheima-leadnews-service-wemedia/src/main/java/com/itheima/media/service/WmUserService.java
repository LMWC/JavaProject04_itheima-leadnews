package com.itheima.media.service;

import com.itheima.common.exception.LeadNewsException;
import com.itheima.media.pojo.WmUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.media.vo.LoginMediaVo;

import java.util.Map;

/**
 * <p>
 * 自媒体用户信息表 服务类
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
public interface WmUserService extends IService<WmUser> {


    Map<String, Object> login(LoginMediaVo loginMediaVo) throws LeadNewsException;
}
