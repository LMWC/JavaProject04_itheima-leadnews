package com.itheima.sync.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.article.pojo.ApArticle;

import java.util.concurrent.CountDownLatch;

public interface ArticleService {
    void importAll(Long page, Long size, CountDownLatch countDownLatch);
    Long selectCount();

}

