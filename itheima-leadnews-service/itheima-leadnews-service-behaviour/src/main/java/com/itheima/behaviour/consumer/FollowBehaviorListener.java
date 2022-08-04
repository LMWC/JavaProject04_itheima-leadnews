package com.itheima.behaviour.consumer;

import com.alibaba.fastjson.JSON;
import com.itheima.behaviour.dto.FollowBehaviorDto;
import com.itheima.behaviour.service.ApFollowBehaviorService;
import com.itheima.common.constants.BusinessConstants;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class FollowBehaviorListener {

    @Autowired
    private ApFollowBehaviorService apFollowBehaviorService;

    @KafkaListener(topics = BusinessConstants.MqConstants.FOLLOW_BEHAVIOR_TOPIC)
    public void receiverMessage(ConsumerRecord<String,String> record){
        //接收到消息JSON
        String jsonstr = record.value();
        //转成对象
        FollowBehaviorDto followBehaviorDto = JSON.parseObject(jsonstr, FollowBehaviorDto.class);
        //添加到表中
        apFollowBehaviorService.saveFollowBehavior(followBehaviorDto);
    }
}
