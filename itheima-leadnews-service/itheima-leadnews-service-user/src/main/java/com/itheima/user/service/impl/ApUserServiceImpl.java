package com.itheima.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.common.exception.LeadNewsException;
import com.itheima.common.util.au.JwtUtil;
import com.itheima.common.util.au.TokenJsonVo;
import com.itheima.common.util.au.TokenRole;
import com.itheima.common.util.au.UserTokenInfo;
import com.itheima.media.pojo.WmUser;
import com.itheima.user.dto.LoginDto;
import com.itheima.user.pojo.ApUser;
import com.itheima.user.mapper.ApUserMapper;
import com.itheima.user.service.ApUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * <p>
 * APP用户信息表 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
@Service
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {
    @Autowired
    private ApUserMapper apUserMapper;

    @Override
    public TokenJsonVo login(LoginDto loginDto) throws LeadNewsException {
        //1.判断是否为空 为空 则报错提示不能为空
        if (StringUtils.isEmpty(loginDto.getPhone()) || StringUtils.isEmpty(loginDto.getPassword())) {
            throw new LeadNewsException("手机号和密码不能为空");
        }

        //2.根据用户名 从数据库查询数据 如果没有找到 报错
        QueryWrapper<ApUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", loginDto.getPhone());
        ApUser apUserFromDb = apUserMapper.selectOne(queryWrapper);
        if (apUserFromDb == null) {
            throw new LeadNewsException("手机号或者密码错误");
        }

        //3.获取页面传递过来的密码 进行md5加密   再和数据库的密文进行对比  如果对比失败  报错
        String temp = loginDto.getPassword() + apUserFromDb.getSalt();
        String passwordFromWebMd5 = DigestUtils.md5DigestAsHex(temp.getBytes());
        if (!passwordFromWebMd5.equals(apUserFromDb.getPassword())) {
            throw new LeadNewsException("手机号或者密码错误");
        }

        //4.生成令牌返回给前端  用户的ID 和用户的角色
        UserTokenInfo userTokenInfo = new UserTokenInfo(
                Long.valueOf(apUserFromDb.getId()),
                apUserFromDb.getImage(),
                apUserFromDb.getName(),
                apUserFromDb.getName(),
                TokenRole.ROLE_APP
        );
        TokenJsonVo token = JwtUtil.createToken(userTokenInfo);

        //info.put("nickName",adUserFromDb.getNickname());
        return token;
    }
}
