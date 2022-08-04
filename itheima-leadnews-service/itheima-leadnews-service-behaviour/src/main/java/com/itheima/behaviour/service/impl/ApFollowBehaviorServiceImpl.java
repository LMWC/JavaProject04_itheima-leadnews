package com.itheima.behaviour.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.behaviour.dto.FollowBehaviorDto;
import com.itheima.behaviour.mapper.ApBehaviorEntryMapper;
import com.itheima.behaviour.mapper.ApFollowBehaviorMapper;
import com.itheima.behaviour.mapper.ApForwardBehaviorMapper;
import com.itheima.behaviour.pojo.ApBehaviorEntry;
import com.itheima.behaviour.pojo.ApFollowBehavior;
import com.itheima.behaviour.pojo.ApForwardBehavior;
import com.itheima.behaviour.service.ApBehaviorEntryService;
import com.itheima.behaviour.service.ApFollowBehaviorService;
import com.itheima.common.constants.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * APP关注行为表 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2022-08-03
 */
@Service
public class ApFollowBehaviorServiceImpl extends ServiceImpl<ApFollowBehaviorMapper, ApFollowBehavior> implements ApFollowBehaviorService {

    @Autowired
    private ApFollowBehaviorMapper apFollowBehaviorMapper;

    @Autowired
    private ApBehaviorEntryService apBehaviorEntryService;

    //业务 放到业务层
    @Override
    public void saveFollowBehavior(FollowBehaviorDto followBehaviorDto) {

        ApBehaviorEntry entry = apBehaviorEntryService.findByUserIdOrEquipmentId(followBehaviorDto.getUserId(), SystemConstants.TYPE_USER);
        if(entry==null){
            //日志打印
            return;
        }
        ApFollowBehavior record = new ApFollowBehavior();
        record.setArticleId(followBehaviorDto.getArticleId());
        record.setCreatedTime(LocalDateTime.now());
        record.setFollowId(followBehaviorDto.getFollowId());

        record.setEntryId(entry.getId());//需要查询出来
        apFollowBehaviorMapper.insert(record);
    }
}
