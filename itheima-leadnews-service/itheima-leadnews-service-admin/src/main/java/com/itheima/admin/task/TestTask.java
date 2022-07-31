package com.itheima.admin.task;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestTask {

    //格式要求的
    @XxlJob("testTask")
    public ReturnT<String> testTask(String param) throws Exception {
        System.out.println("执行任务========1111"+new Date());
        return ReturnT.SUCCESS;
    }
}
