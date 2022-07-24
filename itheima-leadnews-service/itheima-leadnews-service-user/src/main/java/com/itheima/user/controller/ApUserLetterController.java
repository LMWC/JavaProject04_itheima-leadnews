package com.itheima.user.controller;


import com.itheima.user.pojo.ApUserLetter;
import com.itheima.user.service.ApUserLetterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.core.controller.AbstractCoreController;

/**
* <p>
* APP用户私信信息表 控制器</p>
* @author ljh
* @since 2021-12-22
*/
@Api(value="APP用户私信信息表",tags = "ApUserLetterController")
@RestController
@RequestMapping("/apUserLetter")
public class ApUserLetterController extends AbstractCoreController<ApUserLetter> {

    private ApUserLetterService apUserLetterService;

    //注入
    @Autowired
    public ApUserLetterController(ApUserLetterService apUserLetterService) {
        super(apUserLetterService);
        this.apUserLetterService=apUserLetterService;
    }

}

