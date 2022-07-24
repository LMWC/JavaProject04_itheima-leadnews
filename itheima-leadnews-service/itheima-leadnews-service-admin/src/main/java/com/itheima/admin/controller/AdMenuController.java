package com.itheima.admin.controller;


import com.itheima.admin.pojo.AdMenu;
import com.itheima.admin.service.AdMenuService;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* 菜单资源信息表 控制器</p>
* @author ljh
* @since 2022-07-22
*/
@Api(value="菜单资源信息表",tags = "AdMenuController")
@RestController
@RequestMapping("/adMenu")
public class AdMenuController extends AbstractCoreController<AdMenu> {

    private AdMenuService adMenuService;

    //注入
    @Autowired
    public AdMenuController(AdMenuService adMenuService) {
        super(adMenuService);
        this.adMenuService=adMenuService;
    }

}

