package com.itheima.article.controller;


import com.itheima.article.pojo.ApAssociateWords;
import com.itheima.article.service.ApAssociateWordsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.core.controller.AbstractCoreController;

/**
* <p>
* 联想词表 控制器</p>
* @author ljh
* @since 2021-12-22
*/
@Api(value="联想词表",tags = "ApAssociateWordsController")
@RestController
@RequestMapping("/apAssociateWords")
public class ApAssociateWordsController extends AbstractCoreController<ApAssociateWords> {

    private ApAssociateWordsService apAssociateWordsService;

    //注入
    @Autowired
    public ApAssociateWordsController(ApAssociateWordsService apAssociateWordsService) {
        super(apAssociateWordsService);
        this.apAssociateWordsService=apAssociateWordsService;
    }

}

