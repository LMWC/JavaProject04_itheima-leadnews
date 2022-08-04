package com.itheima.behaviour.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.behaviour.dto.LikesBehaviourDto;
import com.itheima.behaviour.mapper.ApLikesBehaviorMapper;
import com.itheima.behaviour.pojo.ApBehaviorEntry;
import com.itheima.behaviour.pojo.ApLikesBehavior;
import com.itheima.behaviour.service.ApBehaviorEntryService;
import com.itheima.behaviour.service.ApLikesBehaviorService;
import com.itheima.common.constants.SystemConstants;
import com.itheima.common.exception.LeadNewsException;
import com.itheima.common.util.RequestContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * APP点赞行为表 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2022-08-03
 */
@Service
public class ApLikesBehaviorServiceImpl extends ServiceImpl<ApLikesBehaviorMapper, ApLikesBehavior> implements ApLikesBehaviorService {

    @Autowired
    private ApBehaviorEntryService apBehaviorEntryService;

    @Autowired
    private ApLikesBehaviorMapper apLikesBehaviorMapper;

    @Override
    public void like(LikesBehaviourDto likesBehaviourDto) throws LeadNewsException {
        //1.判断是否是匿名用户 如果是 则获取到实体对象（设备）
        boolean anonymous = RequestContextUtil.isAnonymous();
        Integer userId = RequestContextUtil.getUserId();
        ApBehaviorEntry entry = null;
        if (anonymous) {
            entry = apBehaviorEntryService.findByUserIdOrEquipmentId(likesBehaviourDto.getEquipmentId(), SystemConstants.TYPE_E);
        } else {
            //2.判断是否是匿名用户 如果不是 则获取到实体对象（用户）
            entry = apBehaviorEntryService.findByUserIdOrEquipmentId(userId, SystemConstants.TYPE_USER);
        }
        if (entry == null) {
            throw new LeadNewsException("点赞失败");
        }
        QueryWrapper<ApLikesBehavior> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("entry_id",entry.getId());
        queryWrapper.eq("article_id",likesBehaviourDto.getArticleId());
        ApLikesBehavior apLikesBehavior = apLikesBehaviorMapper.selectOne(queryWrapper);
        if (likesBehaviourDto.getOperation() == 1) {
            //3.判断 如果是点赞 则 添加或者更新数据

            //3.1 先查询 如果没查询到 则insert


            if(apLikesBehavior==null){
                ApLikesBehavior entity = new ApLikesBehavior();
                entity.setArticleId(likesBehaviourDto.getArticleId());
                entity.setCreatedTime(LocalDateTime.now());
                entity.setEntryId(entry.getId());
                entity.setOperation(likesBehaviourDto.getOperation());
                entity.setType(likesBehaviourDto.getType());
                apLikesBehaviorMapper.insert(entity);
            }else {
                //3.2 如果查询到了 并且是 取消点赞的状态 才可以更新
                if(apLikesBehavior.getOperation()==0){
                    apLikesBehavior.setOperation(1);
                    apLikesBehaviorMapper.updateById(apLikesBehavior);
                }else {
                    //查询到的本身就是已经点赞的状态了 你还来点赞 多此一举
                    throw new LeadNewsException("你已经点了");
                }
            }



        } else {
            //4.判断如果是取消点赞 则 更新数据
            if(apLikesBehavior!=null && apLikesBehavior.getOperation()==1){
                apLikesBehavior.setOperation(0);
                apLikesBehaviorMapper.updateById(apLikesBehavior);
            }

        }
    }
}
