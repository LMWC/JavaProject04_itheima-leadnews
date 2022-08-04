package com.itheima.sync.service.impl;

import com.alibaba.fastjson.JSON;
import com.itheima.article.pojo.ApArticle;
import com.itheima.search.document.ArticleInfoDocument;
import com.itheima.sync.mapper.ArticleMapper;
import com.itheima.sync.repository.DocumentRepository;
import com.itheima.sync.service.ArticleService;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    @Autowired
    private DocumentRepository documentRepository;


    @Override
    @Async("taskExecutor")
    public void importAll(Long page, Long size, CountDownLatch countDownLatch) {
        //1分页查询到数据
        List<ApArticle> apArticles = articleMapper.selectByPage((page - 1) * size, size);
        //2.分批导入到ES中
        //BulkRequest bulkRequest = new BulkRequest("article");
        //bulkRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);
        try {
            /*List<ArticleInfoDocument> articleInfoDocuments = JSON.parseArray(JSON.toJSONString(apArticles), ArticleInfoDocument.class);
            articleInfoDocuments.forEach(info -> bulkRequest.add(new IndexRequest().id(info.getId().toString()).source(JSON.toJSONString(info), XContentType.JSON)));
            restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);*/
            documentRepository.saveAll(JSON.parseArray(JSON.toJSONString(apArticles), ArticleInfoDocument.class));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //减掉数量
            countDownLatch.countDown();
        }

    }

    @Override
    public Long selectCount() {
        return articleMapper.selectCount();
    }
}
