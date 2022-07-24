package com.itheima.admin.controller;

import com.itheima.admin.service.AdUserService;
import com.itheima.admin.vo.LoginAdminVo;
import com.itheima.common.exception.LeadNewsException;
import com.itheima.common.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private AdUserService adUserService;

    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody LoginAdminVo loginAdminVo) throws LeadNewsException {
        Map<String,Object> info = adUserService.login(loginAdminVo);
        return Result.ok(info);
    }

    public static void main(String[] args) {
        String str = "123456"+"abcd";
        String miwen = DigestUtils.md5DigestAsHex(str.getBytes());
        System.out.println(miwen);
    }
}
