package com.itheima.user.dto;

import lombok.Data;

@Data

public class LoginDto {
    //设备id
    private Integer equipmentId;

    //0 表示 不登录先看看   1表示 需要登录 默认为 1
    private Integer flag = 1;

    //手机号
    private String phone;

    //密码
    private String password;
}