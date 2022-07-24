package com.itheima.media.controller;


import com.itheima.media.pojo.WmUserEquipment;
import com.itheima.media.service.WmUserEquipmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.core.controller.AbstractCoreController;

/**
* <p>
* 自媒体用户设备信息表 控制器</p>
* @author ljh
* @since 2021-12-22
*/
@Api(value="自媒体用户设备信息表",tags = "WmUserEquipmentController")
@RestController
@RequestMapping("/wmUserEquipment")
public class WmUserEquipmentController extends AbstractCoreController<WmUserEquipment> {

    private WmUserEquipmentService wmUserEquipmentService;

    //注入
    @Autowired
    public WmUserEquipmentController(WmUserEquipmentService wmUserEquipmentService) {
        super(wmUserEquipmentService);
        this.wmUserEquipmentService=wmUserEquipmentService;
    }

}

