package com.itheima.search.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class BussinessService {
    //需要异步处理（多线程处理）
    @Async("taskExecutor")
    public void handler(){
        //获取某一个页数据 批量导入到ES中业务
        System.out.println("==========="+ Thread.currentThread().getName());
    }
}
