package com.itheima.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.admin.pojo.AdChannel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ChannelMapper extends BaseMapper<AdChannel> {

    /*@Select(value="select * from ad_channel")
    List<AdChannel> findAll();*/
}
