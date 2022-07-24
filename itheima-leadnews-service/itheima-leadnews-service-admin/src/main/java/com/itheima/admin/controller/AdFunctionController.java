package com.itheima.admin.controller;


import com.itheima.admin.pojo.AdFunction;
import com.itheima.admin.service.AdFunctionService;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* 页面功能信息表 控制器</p>
* @author ljh
* @since 2022-07-22
*/
@Api(value="页面功能信息表",tags = "AdFunctionController")
@RestController
@RequestMapping("/adFunction")
public class AdFunctionController extends AbstractCoreController<AdFunction> {

    private AdFunctionService adFunctionService;

    //注入
    @Autowired
    public AdFunctionController(AdFunctionService adFunctionService) {
        super(adFunctionService);
        this.adFunctionService=adFunctionService;
    }

}

