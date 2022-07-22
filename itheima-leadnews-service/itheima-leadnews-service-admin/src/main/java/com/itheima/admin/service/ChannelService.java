package com.itheima.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.admin.pojo.AdChannel;
import com.itheima.common.pojo.PageInfo;
import com.itheima.common.pojo.PageRequestDto;

import java.util.List;

public interface ChannelService extends IService<AdChannel> {

    PageInfo<AdChannel> search(PageRequestDto<AdChannel> pageRequestDto);

//    List<AdChannel> findAll();

}
