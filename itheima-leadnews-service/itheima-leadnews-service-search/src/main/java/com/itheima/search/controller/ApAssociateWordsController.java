package com.itheima.search.controller;


import com.itheima.common.pojo.Result;
import com.itheima.core.controller.AbstractCoreController;
import com.itheima.search.dto.SearchDto;
import com.itheima.search.pojo.ApAssociateWords;
import com.itheima.search.service.ApAssociateWordsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* <p>
* 联想词表 控制器</p>
* @author ljh
* @since 2022-08-04
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

    @PostMapping("/searchTen")
    public Result<List<String>> searchTen(@RequestBody SearchDto searchDto){
        List<String> list =  apAssociateWordsService.searchTen(searchDto);
        return Result.ok(list);
    }

}

