package com.itheima.search.controller;

import com.itheima.common.pojo.PageInfo;
import com.itheima.common.pojo.Result;
import com.itheima.search.document.ArticleInfoDocument;
import com.itheima.search.dto.SearchDto;

import com.itheima.search.service.ApUserSearchService;
import com.itheima.search.service.ArticleInfoDocumentSearchService;
import com.itheima.search.task.BussinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/article")
public class SearchController {
    @Autowired
    private ArticleInfoDocumentSearchService searchService;

    @PostMapping("/search")
    public Result<PageInfo<ArticleInfoDocument>> search(@RequestBody SearchDto searchDto){
        PageInfo<ArticleInfoDocument> pageInfo = searchService.search(searchDto);
        return Result.ok(pageInfo);
    }

    @Autowired
    private ApUserSearchService apUserSearchService;
    //find?eqId=
    @GetMapping("/find/{eqId}")
    public Set<String> findFromRedis(@PathVariable(name="eqId") Integer eqId){
        return apUserSearchService.findByEqId(eqId);
    }


    @Autowired
    private BussinessService service;


    @GetMapping("/test")
    public String handler(){
        System.out.println("线程名称======="+Thread.currentThread().getName());
        //模拟分页查询
        for (int i = 1; i <= 20; i++) {
            service.handler();//交给一个线程处理
        }


        return "chenggon";
    }

}
