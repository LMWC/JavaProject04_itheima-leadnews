package com.itheima.admin.controller;

import com.itheima.admin.pojo.AdChannel;
import com.itheima.admin.service.ChannelService;
import com.itheima.common.pojo.PageInfo;
import com.itheima.common.pojo.PageRequestDto;
import com.itheima.common.pojo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/channel")
@Api(value = "value值", tags = "频道管理", description = "频道管理的接口相关")
public class ChannelController {

    @Autowired
    private ChannelService channelService;


    //接口
    @GetMapping("/all")
    @ApiOperation(value = "查询所有频道", notes = "注意啦这个是查询所有的频道", tags = "频道管理")
    public Result<List<AdChannel>> findAll() {
        //list的数据是从数据库查出来的
        List<AdChannel> list = channelService.list();
        return Result.ok(list);
    }

    //另外一个接口

    @PostMapping("/search")
    public Result<PageInfo<AdChannel>> search(@RequestBody PageRequestDto<AdChannel> pageRequestDto) {
        PageInfo<AdChannel> info = channelService.search(pageRequestDto);
        return Result.ok(info);
    }

    //新增
    @PostMapping
    public Result insert(@RequestBody AdChannel adChannel) {
        channelService.save(adChannel);
        return Result.ok();
    }


    //删除根据ID
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable(name = "id") Integer id) {
        channelService.removeById(id);
        return Result.ok();
    }

    //根据ID获取频道 先回显

    @GetMapping("/{id}")
    public Result<AdChannel> getById(@PathVariable(name = "id") Integer id) {
        AdChannel adChannel = channelService.getById(id);
        return Result.ok(adChannel);
    }

    @PutMapping
    public Result updateById(@RequestBody AdChannel adChannel) {
        //update xxx set name=? wehre id=?
        if (adChannel.getId() == null) {
            return Result.error();
        }
        channelService.updateById(adChannel);
        return Result.ok();
    }


}
