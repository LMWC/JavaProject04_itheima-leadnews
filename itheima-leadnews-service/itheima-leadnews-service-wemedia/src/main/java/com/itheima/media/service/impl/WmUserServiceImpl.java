package com.itheima.media.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.common.exception.LeadNewsException;
import com.itheima.common.util.AppJwtUtil;
import com.itheima.common.util.au.JwtUtil;
import com.itheima.common.util.au.TokenJsonVo;
import com.itheima.common.util.au.TokenRole;
import com.itheima.common.util.au.UserTokenInfo;
import com.itheima.media.pojo.WmUser;
import com.itheima.media.mapper.WmUserMapper;
import com.itheima.media.service.WmUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.media.vo.LoginMediaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 自媒体用户信息表 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
@Service
public class WmUserServiceImpl extends ServiceImpl<WmUserMapper, WmUser> implements WmUserService {
    @Autowired
    private WmUserMapper wmUserMapper;

   /* @Override
    public Map<String, Object> login(LoginMediaVo loginMediaVo) throws LeadNewsException {
        //1.判断是否为空 为空 则报错提示不能为空
        if (StringUtils.isEmpty(loginMediaVo.getName()) || StringUtils.isEmpty(loginMediaVo.getPassword())) {
            throw new LeadNewsException("用户名和密码不能为空");
        }

        //2.根据用户名 从数据库查询数据 如果没有找到 报错
        QueryWrapper<WmUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", loginMediaVo.getName());
        WmUser wmUserFromDb = wmUserMapper.selectOne(queryWrapper);
        if (wmUserFromDb == null) {
            throw new LeadNewsException("用户名或者密码错误");
        }

        //3.获取页面传递过来的密码 进行md5加密   再和数据库的密文进行对比  如果对比失败  报错
        String temp = loginMediaVo.getPassword() + wmUserFromDb.getSalt();
        String passwordFromWebMd5 = DigestUtils.md5DigestAsHex(temp.getBytes());
        if (!passwordFromWebMd5.equals(wmUserFromDb.getPassword())) {
            throw new LeadNewsException("用户名或者密码错误");
        }

        //4.生成令牌返回给前端
        String token = AppJwtUtil.createToken(Long.valueOf(wmUserFromDb.getId()));
        Map<String, Object> info = new HashMap<>();
        info.put("token", token);
        info.put("headPic", wmUserFromDb.getImage());
        info.put("nickName", wmUserFromDb.getNickname());
        //info.put("nickName",adUserFromDb.getNickname());
        return info;
    }*/

    public TokenJsonVo login(LoginMediaVo loginMediaVo) throws LeadNewsException {
        //1.判断是否为空 为空 则报错提示不能为空
        if (StringUtils.isEmpty(loginMediaVo.getName()) || StringUtils.isEmpty(loginMediaVo.getPassword())) {
            throw new LeadNewsException("用户名和密码不能为空");
        }

        //2.根据用户名 从数据库查询数据 如果没有找到 报错
        QueryWrapper<WmUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", loginMediaVo.getName());
        WmUser wmUserFromDb = wmUserMapper.selectOne(queryWrapper);
        if (wmUserFromDb == null) {
            throw new LeadNewsException("用户名或者密码错误");
        }

        //3.获取页面传递过来的密码 进行md5加密   再和数据库的密文进行对比  如果对比失败  报错
        String temp = loginMediaVo.getPassword() + wmUserFromDb.getSalt();
        String passwordFromWebMd5 = DigestUtils.md5DigestAsHex(temp.getBytes());
        if (!passwordFromWebMd5.equals(wmUserFromDb.getPassword())) {
            throw new LeadNewsException("用户名或者密码错误");
        }

        //4.生成令牌返回给前端  用户的ID 和用户的角色
        UserTokenInfo userTokenInfo = new UserTokenInfo(
                Long.valueOf(wmUserFromDb.getId()),
                wmUserFromDb.getImage(),
                wmUserFromDb.getNickname(),
                wmUserFromDb.getName(),
                TokenRole.ROLE_MEDIA
        );
        TokenJsonVo token = JwtUtil.createToken(userTokenInfo);

        //info.put("nickName",adUserFromDb.getNickname());
        return token;
    }
}
