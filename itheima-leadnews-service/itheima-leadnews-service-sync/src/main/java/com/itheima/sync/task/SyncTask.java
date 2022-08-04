package com.itheima.sync.task;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.article.pojo.ApArticle;
import com.itheima.search.document.ArticleInfoDocument;
import com.itheima.sync.mapper.ArticleMapper;
import com.itheima.sync.repository.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static com.itheima.sync.util.SyncUtil.dateTimeFormatter;

@Component
@Slf4j
public class SyncTask {

    @Autowired
    private DocumentRepository repository;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 每隔30S执行一次  这个可以根据不同的业务场景选择不同的时间点
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void taskExcutor() {
        String recordTime = stringRedisTemplate.opsForValue()
                .get("recordTime");
        System.out.println("==========");
        if (StringUtils.isEmpty(recordTime)) {
            log.info("执行定时任务taskExcutor:: 上一次更新时间为null,此次不执行");
            return;
        }
        log.info("执行定时任务taskExcutor:: 执行时间" + LocalDateTime.now());
        //格式化数据 重新设置了更新的时间
        stringRedisTemplate.opsForValue()
                .set("recordTime",
                        LocalDateTime.now(ZoneId.systemDefault()).format(dateTimeFormatter));
        //按照条件查询 select * from article where publishtime>获取到上一次的更新时间
        List<ApArticle> apArticles = articleMapper.selectForCondition(LocalDateTime.parse(recordTime, dateTimeFormatter));
        if (apArticles != null && apArticles.size() > 0) {
            log.info(apArticles.toString());
            List<ArticleInfoDocument> documents = JSON.parseArray(JSON.toJSONString(apArticles), ArticleInfoDocument.class);
            repository.saveAll(documents);
        }
    }
}
