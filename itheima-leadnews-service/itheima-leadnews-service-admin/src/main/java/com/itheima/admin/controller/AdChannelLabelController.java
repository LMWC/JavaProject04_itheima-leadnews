package com.itheima.admin.controller;


import com.itheima.admin.pojo.AdChannelLabel;
import com.itheima.admin.service.AdChannelLabelService;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* 频道标签信息表 控制器</p>
* @author ljh
* @since 2022-07-22
*/
@Api(value="频道标签信息表",tags = "AdChannelLabelController")
@RestController
@RequestMapping("/adChannelLabel")
public class AdChannelLabelController extends AbstractCoreController<AdChannelLabel> {

    private AdChannelLabelService adChannelLabelService;

    //注入
    @Autowired
    public AdChannelLabelController(AdChannelLabelService adChannelLabelService) {
        super(adChannelLabelService);
        this.adChannelLabelService=adChannelLabelService;
    }

}

