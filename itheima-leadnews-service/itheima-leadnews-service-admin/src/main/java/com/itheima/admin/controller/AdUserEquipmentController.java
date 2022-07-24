package com.itheima.admin.controller;


import com.itheima.admin.pojo.AdUserEquipment;
import com.itheima.admin.service.AdUserEquipmentService;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* 管理员设备信息表 控制器</p>
* @author ljh
* @since 2022-07-22
*/
@Api(value="管理员设备信息表",tags = "AdUserEquipmentController")
@RestController
@RequestMapping("/adUserEquipment")
public class AdUserEquipmentController extends AbstractCoreController<AdUserEquipment> {

    private AdUserEquipmentService adUserEquipmentService;

    //注入
    @Autowired
    public AdUserEquipmentController(AdUserEquipmentService adUserEquipmentService) {
        super(adUserEquipmentService);
        this.adUserEquipmentService=adUserEquipmentService;
    }

}

