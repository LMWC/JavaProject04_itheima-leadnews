package com.itheima.behaviour.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.behaviour.dto.ReadBehaviorDto;
import com.itheima.behaviour.mapper.ApReadBehaviorMapper;
import com.itheima.behaviour.pojo.ApBehaviorEntry;
import com.itheima.behaviour.pojo.ApReadBehavior;
import com.itheima.behaviour.service.ApBehaviorEntryService;
import com.itheima.behaviour.service.ApReadBehaviorService;
import com.itheima.common.constants.SystemConstants;
import com.itheima.common.exception.LeadNewsException;
import com.itheima.common.util.RequestContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * APP阅读行为表 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2022-08-03
 */
@Service
public class ApReadBehaviorServiceImpl extends ServiceImpl<ApReadBehaviorMapper, ApReadBehavior> implements ApReadBehaviorService {

    @Autowired
    private ApBehaviorEntryService apBehaviorEntryService;

    @Autowired
    private ApReadBehaviorMapper apReadBehaviorMapper;

    // synchronized  本地锁

    // 分布式锁（1.基于mysql来实现(文件 性能差) 2基于redis来实现（内存中 性能好） 3基于zookeeper来实现 4.基于consul来实现）
    @Override
    public void read(ReadBehaviorDto readBehaviorDto) {

       //整体如果没有记录 就添加 如果有就 更新

        Integer userId = RequestContextUtil.getUserId();
        ApBehaviorEntry entry = null;
        //1.获取当前用户ID 判断是否是匿名用户
        if (RequestContextUtil.isAnonymous()) {
            //2.如果是匿名用户 获取实体对象（设备）
            entry = apBehaviorEntryService.findByUserIdOrEquipmentId(readBehaviorDto.getEquipmentId(), SystemConstants.TYPE_E);
        }else{
            //3.如果是真实用户 获取实体对象（用户）
            entry = apBehaviorEntryService.findByUserIdOrEquipmentId(userId, SystemConstants.TYPE_USER);
        }
        if(entry==null){
           return;
        }
        //4.获取阅读记录  如果有更新 如果没有添加
        QueryWrapper<ApReadBehavior> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("entry_id",entry.getId());
        queryWrapper.eq("article_id",readBehaviorDto.getArticleId());
        ApReadBehavior apReadBehavior = apReadBehaviorMapper.selectOne(queryWrapper);
        if(apReadBehavior==null){
            //新增
            apReadBehavior = new ApReadBehavior();
            apReadBehavior.setArticleId(readBehaviorDto.getArticleId());
            apReadBehavior.setEntryId(entry.getId());
            apReadBehavior.setCount(1);
            apReadBehavior.setLoadDuration(readBehaviorDto.getLoadDuration());
            apReadBehavior.setReadDuration(readBehaviorDto.getReadDuration());
            apReadBehavior.setPercentage(readBehaviorDto.getPercentage());
            apReadBehavior.setCreatedTime(LocalDateTime.now());
            apReadBehavior.setUpdatedTime(LocalDateTime.now());
            apReadBehaviorMapper.insert(apReadBehavior);
        }else{
            //更新  线程安全的问题！ 怎么解决
            apReadBehavior.setArticleId(readBehaviorDto.getArticleId());
            apReadBehavior.setLoadDuration(readBehaviorDto.getLoadDuration());
            apReadBehavior.setReadDuration(readBehaviorDto.getReadDuration());
            apReadBehavior.setPercentage(readBehaviorDto.getPercentage());
            apReadBehavior.setUpdatedTime(LocalDateTime.now());
            apReadBehavior.setCount(apReadBehavior.getCount()+1);

           apReadBehaviorMapper.updateById(apReadBehavior);

            // update 表 set count=count+1 where id=1 for update   (数据库的行锁： 实际上 如果使用innodb引擎才会 for update不用写默认就有)
        }


    }
}
