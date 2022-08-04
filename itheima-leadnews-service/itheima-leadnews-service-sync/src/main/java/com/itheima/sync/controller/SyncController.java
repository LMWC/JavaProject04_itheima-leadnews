package com.itheima.sync.controller;

import com.itheima.common.pojo.Result;
import com.itheima.sync.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.CountDownLatch;

import static com.itheima.sync.util.SyncUtil.dateTimeFormatter;

@RestController
@RequestMapping("/sync")
public class SyncController {
    @Autowired
    private ArticleService articleService;
    //这个可以调整
    private static final Long size = 5000L;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //多线程方式进行导入

    /**
     * 此方法 需要拥有超级管理员的身份人进行操作
     *
     * @return
     */
    @GetMapping("/importAll")
    public Result importAll() {

        Long startTime = System.currentTimeMillis();
        //一把锁
        Boolean flag = stringRedisTemplate.opsForValue()
                .setIfAbsent("recordTime",
                        LocalDateTime.now(ZoneId.systemDefault()).format(dateTimeFormatter));
        if (flag) {
            //1.查询统计所有数量  多少页 需要多少线程来处理呢？
            Long total = articleService.selectCount();
            Long totalPages = total % size > 0 ? (total / size) + 1 : total / size;
            final CountDownLatch countDownLatch = new CountDownLatch(totalPages.intValue());
            //2.执行多线程方法

            for (Long page = 1L; page <= totalPages; page++) {
                articleService.importAll(page, size, countDownLatch);
            }
            //3.设置等待 等到 CountDownLatch中的数量减为0之后执行
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            return Result.error("正在执行中");
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("kkkk>>>" + (endTime - startTime));
        return Result.ok();
    }

    public static void main(String[] args) {
        String format = LocalDateTime.now(ZoneId.systemDefault()).format(dateTimeFormatter);
        System.out.println(format);
    }
}
