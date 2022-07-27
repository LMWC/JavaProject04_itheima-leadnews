package com.itheima.common.util.au;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTokenInfo implements Serializable {

    //用户ID
    private Long userId;

    //用户头像
    private String headImage;

    //用户昵称
    private String nickName;

    //用户名称
    private String name;

    //当前用户的角色
    private TokenRole role;

    //获取匿名用户
    public static UserTokenInfo getAnonymous(){
        return new UserTokenInfo(0L,null,null,null,TokenRole.ROLE_ANONYMOUS);
    }


}