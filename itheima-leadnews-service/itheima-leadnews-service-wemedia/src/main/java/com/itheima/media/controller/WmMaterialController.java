package com.itheima.media.controller;


import com.itheima.media.pojo.WmMaterial;
import com.itheima.media.service.WmMaterialService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.core.controller.AbstractCoreController;

/**
* <p>
* 自媒体图文素材信息表 控制器</p>
* @author ljh
* @since 2021-12-22
*/
@Api(value="自媒体图文素材信息表",tags = "WmMaterialController")
@RestController
@RequestMapping("/wmMaterial")
public class WmMaterialController extends AbstractCoreController<WmMaterial> {

    private WmMaterialService wmMaterialService;

    //注入
    @Autowired
    public WmMaterialController(WmMaterialService wmMaterialService) {
        super(wmMaterialService);
        this.wmMaterialService=wmMaterialService;
    }

}

