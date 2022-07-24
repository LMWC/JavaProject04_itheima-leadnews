package com.itheima.user.controller;


import com.itheima.user.pojo.ApUserEquipment;
import com.itheima.user.service.ApUserEquipmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.core.controller.AbstractCoreController;

/**
* <p>
* APP用户设备信息表 控制器</p>
* @author ljh
* @since 2021-12-22
*/
@Api(value="APP用户设备信息表",tags = "ApUserEquipmentController")
@RestController
@RequestMapping("/apUserEquipment")
public class ApUserEquipmentController extends AbstractCoreController<ApUserEquipment> {

    private ApUserEquipmentService apUserEquipmentService;

    //注入
    @Autowired
    public ApUserEquipmentController(ApUserEquipmentService apUserEquipmentService) {
        super(apUserEquipmentService);
        this.apUserEquipmentService=apUserEquipmentService;
    }

}

