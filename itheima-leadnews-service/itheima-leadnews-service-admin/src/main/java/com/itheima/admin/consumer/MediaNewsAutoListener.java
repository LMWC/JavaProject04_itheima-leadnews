package com.itheima.admin.consumer;

import com.itheima.admin.service.WemediaNewsAutoScanService;
import com.itheima.common.constants.BusinessConstants;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MediaNewsAutoListener {

    @Autowired
    private WemediaNewsAutoScanService wemediaNewsAutoScanService;


    //方法的作用就是监听消息 实现业务逻辑（实现自动审核功能）
    @KafkaListener(topics = BusinessConstants.MqConstants.WM_NEWS_AUTO_SCAN_TOPIC)
    public void recevieMessage(ConsumerRecord<String, String> record) {

        //获取消息本身的内容（就是自媒体文章的ID）
        String id = record.value();
        //实现业务审核代码 放到业务类中
        try {
            wemediaNewsAutoScanService.autoScanByMediaNewsId(Integer.valueOf(id));
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

}
