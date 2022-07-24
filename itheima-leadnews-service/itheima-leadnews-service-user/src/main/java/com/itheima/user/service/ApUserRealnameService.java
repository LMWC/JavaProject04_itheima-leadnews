package com.itheima.user.service;

import com.itheima.user.pojo.ApUserRealname;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * APP实名认证信息表 服务类
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
public interface ApUserRealnameService extends IService<ApUserRealname> {

    
    void pass(Integer id);


    void reject(Integer id, String reason);
}
