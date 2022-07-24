package com.itheima.user.service.impl;

import com.itheima.user.pojo.ApUserMessage;
import com.itheima.user.mapper.ApUserMessageMapper;
import com.itheima.user.service.ApUserMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * APP用户消息通知信息表 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
@Service
public class ApUserMessageServiceImpl extends ServiceImpl<ApUserMessageMapper, ApUserMessage> implements ApUserMessageService {

}
