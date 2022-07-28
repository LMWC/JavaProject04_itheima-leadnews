package com.itheima.common.util.au;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    //加密的秘钥
    private static final byte[] key = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY".getBytes();

    //过期时间单位为 秒
    public static final int TOKEN_TIME_OUT = 30;


    /**
     * @param userToken 用户令牌的封装对象
     * @return 刷新令牌和访问令牌的封装对象
     */
    public static TokenJsonVo createToken(UserTokenInfo userToken) {

        Instant now = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        //访问令牌过期时间
        ZonedDateTime accessExpire = now.atZone(ZoneId.systemDefault()).plusSeconds(TOKEN_TIME_OUT);//1 小时

        //刷新令牌过期时间 两倍
        ZonedDateTime RefreshExpire = now.atZone(ZoneId.systemDefault()).plusSeconds(TOKEN_TIME_OUT * 2);//2 小时

        Date accessDate = Date.from(accessExpire.toInstant());
        Date refreshDate = Date.from(RefreshExpire.toInstant());

        Map<String, Object> tokenMap = JSON.parseObject(JSON.toJSONString(userToken), Map.class);

        //访问令牌
        String accessToken = Jwts.builder()
                .addClaims(tokenMap)
                .signWith(SignatureAlgorithm.HS256, key)
                .setExpiration(accessDate)
                .compact();
        System.out.println("刷新令牌的时间："+refreshDate.getTime());

        //刷新令牌
        String refreshToken = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, key)
                .addClaims(tokenMap)
                .setExpiration(refreshDate)
                .compact();

        //生成两个令牌数据
        TokenJsonVo tokenJsonVo = new TokenJsonVo(accessToken, refreshToken);

        return tokenJsonVo;
    }

    /**
     * 判断是否为正确角色
     *
     * @param tokenInfoExp
     * @param role
     * @return
     */
    public static boolean isValidRole(UserTokenInfoExp tokenInfoExp, TokenRole role) {
        if (tokenInfoExp == null || role == null) {
            return false;
        }
        return tokenInfoExp.getRole() == role;
    }

    /**
     * 判断令牌是否已经过期
     *
     * @param tokenInfoExp
     * @return
     */
    public static boolean isExpire(UserTokenInfoExp tokenInfoExp) {
        if (tokenInfoExp == null) {
            return true;
        }

        // tokenInfoExp.getExp()  是过期时间点（时刻）  15:01分 才过期

        // System.currentTimeMillis() 当前时间点（时刻） 15:00 分

        return tokenInfoExp.getExp() < System.currentTimeMillis();
    }


    //https://www.coder.work/article/1413231
    //https://www.cnblogs.com/goloving/p/14922929.html

    //解析令牌信息  xxx.yyy.zzz  得到的信息
    public static UserTokenInfoExp parseJwtUserToken(String token) {
        Claims body = null;
        if (token == null) {
            throw new IllegalArgumentException("token cannot be null");
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            body = claimsJws.getBody();
            System.out.println(body);
        } catch (ExpiredJwtException e) {
            //照样获取数据
            e.printStackTrace();
            body = e.getClaims();
            UserTokenInfoExp tokenInfoExp = JSON.parseObject(JSON.toJSONString(body), UserTokenInfoExp.class);
            //这里时间转换有问题 需要重新设置一次
            tokenInfoExp.setExp(body.getExpiration().getTime());
            return tokenInfoExp;
        } catch (MalformedJwtException e) {
            //e.printStackTrace();
        } catch (SignatureException e) {
            //e.printStackTrace();
        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
        }
        if (body == null) {
            throw new IllegalArgumentException("body cannot be null");
        }
        UserTokenInfoExp tokenInfoExp = JSON.parseObject(JSON.toJSONString(body), UserTokenInfoExp.class);
        //这里时间转换有问题 需要重新设置一次
        tokenInfoExp.setExp(body.getExpiration().getTime());

        return tokenInfoExp;
    }

    public static void main(String[] args) throws Exception{
        UserTokenInfo userinfo = UserTokenInfo.getAnonymous();
        TokenJsonVo token = JwtUtil.createToken(userinfo);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        UserTokenInfoExp userTokenInfoExp1 = JwtUtil.parseJwtUserToken(token.getRefreshToken());
        TokenJsonVo token1 = JwtUtil.createToken(userTokenInfoExp1);
        System.out.println(token1);


    }



}