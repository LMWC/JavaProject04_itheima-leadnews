package com.itheima.search.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class TestTask {

    //@Scheduled(cron = "0/5 * * * * ? ")
    public void zhixing(){
        //业务逻辑
        System.out.println( new Date());


        //实现分页查询

        for (int i = 1; i <= 100; i++) {
            //1 查询到第一个页的数据-->由一个线程去执行
            //2 查询到的二个页的数据--->由另外一个线程去执行
        }


    }

    public void doTask(){
        //提前准备了一个线程池
        //直接从池子中拿线程执行任务

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                //执行某一个业务
            }
        }).start();*/
    }
}
