package com.itheima.search.controller;


import com.itheima.core.controller.AbstractCoreController;
import com.itheima.search.pojo.ApHotWords;
import com.itheima.search.service.ApHotWordsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* 搜索热词表 控制器</p>
* @author ljh
* @since 2022-08-04
*/
@Api(value="搜索热词表",tags = "ApHotWordsController")
@RestController
@RequestMapping("/apHotWords")
public class ApHotWordsController extends AbstractCoreController<ApHotWords> {

    private ApHotWordsService apHotWordsService;

    //注入
    @Autowired
    public ApHotWordsController(ApHotWordsService apHotWordsService) {
        super(apHotWordsService);
        this.apHotWordsService=apHotWordsService;
    }

}

