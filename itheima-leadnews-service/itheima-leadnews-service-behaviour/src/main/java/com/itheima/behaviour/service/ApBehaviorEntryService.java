package com.itheima.behaviour.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.behaviour.pojo.ApBehaviorEntry;

/**
 * <p>
 * APP行为实体表,一个行为实体可能是用户或者设备，或者其它 服务类
 * </p>
 *
 * @author ljh
 * @since 2022-08-03
 */
public interface ApBehaviorEntryService extends IService<ApBehaviorEntry> {

    //根据用户ID和type获取实体对象

    /**
     *
     * @param userId  可以是设备的ID 值 或者登陆的用户的ID
     * @param type  0   1
     * @return
     */
    public ApBehaviorEntry findByUserIdOrEquipmentId(Integer userId, Integer type);

}
