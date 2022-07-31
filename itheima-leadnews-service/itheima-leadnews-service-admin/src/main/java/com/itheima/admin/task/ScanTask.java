package com.itheima.admin.task;

import com.itheima.admin.service.WemediaNewsAutoScanService;
import com.itheima.media.feign.WmNewsFeign;
import com.itheima.media.pojo.WmNews;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class ScanTask {

    @Autowired
    private WmNewsFeign wmNewsFeign;

    @Autowired
    private WemediaNewsAutoScanService wemediaNewsAutoScanService;

    @XxlJob("scanArticleTask")//任务名称 叫做scanArticleTask
    public ReturnT<String> handlerScanTask(String param) throws Exception {
        //1.查询所有状态为8 的自媒体文章的列表
        List<WmNews> list = wmNewsFeign.findByStatus(8);
        if (list != null) {
            for (WmNews wmNews : list) {
                //2.判断文章的发布时间是否有值
                LocalDateTime publishTime = wmNews.getPublishTime();
                if (publishTime == null) {
                    //2.2 没有   人工审核之后才会出现的状态，修改状态为9 并保存文章到文章微服务中
                    wmNews.setStatus(9);
                    wmNewsFeign.updateByPrimaryKey(wmNews);
                    wemediaNewsAutoScanService.createArticleInfoData(wmNews);

                } else {
                    //2.1 有值   判断 当前时间是否>=发布时间 修改状态为9 并保存文章到文章微服务中
                    LocalDateTime now = LocalDateTime.now();
                    if (now.isAfter(publishTime) || now.isEqual(publishTime)) {
                        wmNews.setStatus(9);
                        wmNewsFeign.updateByPrimaryKey(wmNews);
                        wemediaNewsAutoScanService.createArticleInfoData(wmNews);
                    } else {
                        //说明现在还没到时机 不能更新
                        log.info("定时任务执行，执行查询到的文章的ID是：{}", wmNews.getId());
                        System.out.println("定时任务执行，执行查询到的文章的ID是："+wmNews.getId());
                    }
                }
            }

        }

        return ReturnT.SUCCESS;
    }
}
