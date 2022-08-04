package com.itheima.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.article.feign.ApAuthorFeign;
import com.itheima.article.pojo.ApAuthor;
import com.itheima.behaviour.dto.FollowBehaviorDto;
import com.itheima.common.constants.BusinessConstants;
import com.itheima.common.exception.LeadNewsException;
import com.itheima.common.pojo.Result;
import com.itheima.common.pojo.StatusCode;
import com.itheima.common.util.RequestContextUtil;
import com.itheima.common.util.au.UserTokenInfoExp;
import com.itheima.user.dto.UserRelationDto;
import com.itheima.user.mapper.ApUserFanMapper;
import com.itheima.user.pojo.ApUserFan;
import com.itheima.user.pojo.ApUserFollow;
import com.itheima.user.mapper.ApUserFollowMapper;
import com.itheima.user.service.ApUserFollowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * APP用户关注信息表 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
@Service
public class ApUserFollowServiceImpl extends ServiceImpl<ApUserFollowMapper, ApUserFollow> implements ApUserFollowService {

    @Autowired
    private ApUserFollowMapper apUserFollowMapper;

    @Autowired
    private ApUserFanMapper apUserFanMapper;

    @Autowired
    private ApAuthorFeign apAuthorFeign;

    @Autowired
    private KafkaTemplate kafkaTemplate;


    //根据前端来的操作类型 添加数据或者删除数据（2个表） 张飞 关注了 貂蝉
    @Override
    @Transactional
    public void follow(UserRelationDto userRelationDto) throws LeadNewsException {
        boolean anonymous = RequestContextUtil.isAnonymous();
        if (anonymous) {
            throw new LeadNewsException(StatusCode.NEED_LOGIN.code(), StatusCode.NEED_LOGIN.message());
        }
        UserTokenInfoExp userTokenInfo = RequestContextUtil.getRequestUserTokenInfo();

        Integer operation = userRelationDto.getOperation();
        //现在是user微服务  作者对应的APP用户的ID 在article中 所以需要远程调用下
        Result<ApAuthor> result = apAuthorFeign.findById(userRelationDto.getAuthorId());
        ApAuthor apAuthor = result.getData();
        if (operation == 1) {

            //查询一次
            QueryWrapper<ApUserFollow> queryWrapper = new QueryWrapper<>();
            ;
            queryWrapper.eq("user_id", userTokenInfo.getUserId().intValue());
            queryWrapper.eq("follow_id", apAuthor.getUserId());
            ApUserFollow apUserFollow = apUserFollowMapper.selectOne(queryWrapper);

            if (apUserFollow != null) {
                throw new LeadNewsException("已经关注过了");
            }
            //1.获取关注表对应的POJO设置值 添加数据
            apUserFollow = new ApUserFollow();
            apUserFollow.setUserId(userTokenInfo.getUserId().intValue());//zhangfei
            apUserFollow.setFollowId(apAuthor.getUserId());//貂蝉 app_user的ID
            apUserFollow.setFollowName(userRelationDto.getAuthorName());//前提：用户的名和作者的名一样
            apUserFollow.setLevel(0);//一般
            apUserFollow.setIsNotice(1);//通知
            apUserFollow.setCreatedTime(LocalDateTime.now());
            apUserFollowMapper.insert(apUserFollow);

            //2.获取到粉丝表对应的POJO 设置 添加数据

            ApUserFan apUserFan = new ApUserFan();
            apUserFan.setUserId(apAuthor.getUserId());//貂蝉
            apUserFan.setFansId(userTokenInfo.getUserId().intValue());//张飞
            apUserFan.setFansName(userTokenInfo.getName());//
            apUserFan.setCreatedTime(LocalDateTime.now());
            apUserFan.setIsDisplay(1);
            apUserFan.setIsShieldLetter(1);
            apUserFan.setIsShieldComment(1);
            apUserFanMapper.insert(apUserFan);

            //发送消息
            try {
                FollowBehaviorDto followBehaviorDto = new FollowBehaviorDto();
                followBehaviorDto.setArticleId(userRelationDto.getArticleId());
                followBehaviorDto.setUserId(userTokenInfo.getUserId().intValue());
                followBehaviorDto.setFollowId(apAuthor.getUserId());
                kafkaTemplate.send(BusinessConstants.MqConstants.FOLLOW_BEHAVIOR_TOPIC, JSON.toJSONString(followBehaviorDto));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            //删除
            QueryWrapper<ApUserFan> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("user_id", apAuthor.getUserId());
            queryWrapper1.eq("fans_id", userTokenInfo.getUserId().intValue());
            apUserFanMapper.delete(queryWrapper1);


            QueryWrapper<ApUserFollow> queryWrapper2 = new QueryWrapper<>();
            ;
            queryWrapper2.eq("user_id", userTokenInfo.getUserId().intValue());
            queryWrapper2.eq("follow_id", apAuthor.getUserId());
            apUserFollowMapper.delete(queryWrapper2);
        }

    }
}
