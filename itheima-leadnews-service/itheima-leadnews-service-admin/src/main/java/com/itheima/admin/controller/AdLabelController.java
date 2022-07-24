package com.itheima.admin.controller;


import com.itheima.admin.pojo.AdLabel;
import com.itheima.admin.service.AdLabelService;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* 标签信息表 控制器</p>
* @author ljh
* @since 2022-07-22
*/
@Api(value="标签信息表",tags = "AdLabelController")
@RestController
@RequestMapping("/adLabel")
public class AdLabelController extends AbstractCoreController<AdLabel> {

    private AdLabelService adLabelService;

    //注入
    @Autowired
    public AdLabelController(AdLabelService adLabelService) {
        super(adLabelService);
        this.adLabelService=adLabelService;
    }

}

