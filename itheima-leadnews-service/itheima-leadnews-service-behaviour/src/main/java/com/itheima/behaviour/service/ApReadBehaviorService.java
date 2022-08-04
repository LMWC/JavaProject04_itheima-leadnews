package com.itheima.behaviour.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.behaviour.dto.ReadBehaviorDto;
import com.itheima.behaviour.pojo.ApReadBehavior;

/**
 * <p>
 * APP阅读行为表 服务类
 * </p>
 *
 * @author ljh
 * @since 2022-08-03
 */
public interface ApReadBehaviorService extends IService<ApReadBehavior> {


    void read(ReadBehaviorDto readBehaviorDto);
}
