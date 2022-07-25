package com.itheima.media.controller;


import com.itheima.common.constants.SystemConstants;
import com.itheima.common.pojo.Result;
import com.itheima.common.util.RequestContextUtil;
import com.itheima.media.pojo.WmMaterial;
import com.itheima.media.service.WmMaterialService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.core.controller.AbstractCoreController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
* <p>
* 自媒体图文素材信息表 控制器</p>
* @author ljh
* @since 2021-12-22
*/
@Api(value="自媒体图文素材信息表",tags = "WmMaterialController")
@RestController
@RequestMapping("/wmMaterial")

public class WmMaterialController extends AbstractCoreController<WmMaterial> {

    private WmMaterialService wmMaterialService;

    //注入
    @Autowired
    public WmMaterialController(WmMaterialService wmMaterialService) {
        super(wmMaterialService);
        this.wmMaterialService=wmMaterialService;
    }

    //不能用抽象类的添加素材的方法了 要重写

    //都要加 重写方法    where userid=当前登录的用户的ID

    @PostMapping
    @Override
    public Result<WmMaterial> insert(@RequestBody WmMaterial record) {
        //1.获取当前登录的用户的ID 设置值

        //获取当前请求对象

        String userId = RequestContextUtil.getUserInfo();
        //获取请求头中的 内容
        record.setUserId(Integer.parseInt(userId));
        record.setType(0);
        record.setCreatedTime(LocalDateTime.now());
        record.setIsCollection(0);//默认不收藏
        wmMaterialService.save(record);
        return Result.ok(record);
    }

    //修改
}

