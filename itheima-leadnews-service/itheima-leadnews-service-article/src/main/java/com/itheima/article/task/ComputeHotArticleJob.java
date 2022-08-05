package com.itheima.article.task;

import com.itheima.article.service.ApArticleService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ComputeHotArticleJob {
    @Autowired
    private ApArticleService apArticleService;


    //任务 要做的事情：查询 数据库中的前5天的数据 放入到redis中 zset中
    @XxlJob("computeHotArticleJob")
    public ReturnT<String> handle(String param) throws Exception {
        apArticleService.saveToRedis();
        return ReturnT.SUCCESS;
    }
}
