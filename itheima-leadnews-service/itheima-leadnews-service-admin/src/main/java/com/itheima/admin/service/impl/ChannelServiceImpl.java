package com.itheima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.admin.mapper.ChannelMapper;
import com.itheima.admin.pojo.AdChannel;
import com.itheima.admin.service.ChannelService;
import com.itheima.common.pojo.PageInfo;
import com.itheima.common.pojo.PageRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
//作用是什么？被修饰的类 将来会被springioc容器管理
//@Component  @controller @respository

public class ChannelServiceImpl extends ServiceImpl<ChannelMapper,AdChannel> implements ChannelService {
    @Autowired
    private ChannelMapper channelMapper;

    @Override
    public PageInfo<AdChannel> search(PageRequestDto<AdChannel> pageRequestDto) {

        //select * from ad_channel where name like '%?%' and status=? limit 0,10;


        //1.获取页面传递过来的页码和每页显示的行

        Long page = pageRequestDto.getPage();
        Long size = pageRequestDto.getSize();

        //2.构建分页条件对象
        IPage<AdChannel> pageCondition = new Page<>(page,size);


        //3.获取页面传递过来的查询的参数对象
        AdChannel body = pageRequestDto.getBody();
        QueryWrapper<AdChannel> queryWrapper = new QueryWrapper<AdChannel>();
        //4.条件上的判断 如果有值 则拼接 否则 不拼接
        if(body!=null) {
            if(!StringUtils.isEmpty(body.getName())) {
                queryWrapper.like("name", body.getName());
            }
            if(body.getStatus()!=null) {
                queryWrapper.eq("status", body.getStatus());
            }
        }
        //5.执行分页查询 得到结果
        IPage<AdChannel> resultPage = channelMapper.selectPage(pageCondition, queryWrapper);


        //6.结果封装
        return new PageInfo<AdChannel>(
                resultPage.getCurrent(),
                resultPage.getSize(),
                resultPage.getTotal(),
                resultPage.getPages(),
                resultPage.getRecords());
    }

   /* @Autowired
    private ChannelMapper channelMapper;*/

   /* @Override
    public List<AdChannel> findAll() {
        //查询所有
        return channelMapper.selectList(null);
    }*/
}
