package com.itheima.media.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.common.pojo.PageInfo;
import com.itheima.common.pojo.PageRequestDto;
import com.itheima.common.pojo.Result;
import com.itheima.media.dto.WmNewsDto;
import com.itheima.media.dto.WmNewsDtoSave;
import com.itheima.media.pojo.WmNews;
import com.itheima.media.service.WmNewsService;
import com.itheima.media.vo.WmNewsVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import com.itheima.core.controller.AbstractCoreController;

import java.util.List;

/**
 * <p>
 * 自媒体图文内容信息表 控制器</p>
 *
 * @author ljh
 * @since 2021-12-22
 */
@Api(value = "自媒体图文内容信息表", tags = "WmNewsController")
@RestController
@RequestMapping("/wmNews")
public class WmNewsController extends AbstractCoreController<WmNews> {

    private WmNewsService wmNewsService;

    //注入
    @Autowired
    public WmNewsController(WmNewsService wmNewsService) {
        super(wmNewsService);
        this.wmNewsService = wmNewsService;
    }

    @PostMapping("/searchPage")
    public Result<PageInfo<WmNews>> findByPageDto(@RequestBody PageRequestDto<WmNewsDto> pageRequestDto) {

        PageInfo<WmNews> pageInfo = wmNewsService.findByPageDto(pageRequestDto);
        return Result.ok(pageInfo);
    }

    /**
     * 发表文章
     *
     * @param isSubmit      0 /1
     * @param wmNewsDtoSave
     * @return
     */
    @PostMapping("/save/{isSubmit}")
    public Result save(@PathVariable(name = "isSubmit") Integer isSubmit,
                       @RequestBody WmNewsDtoSave wmNewsDtoSave) {
        if (isSubmit > 1 || isSubmit < 0) {
            return Result.errorMessage("你传递的参数类型有误");
        }
        wmNewsService.save(wmNewsDtoSave, isSubmit);
        return Result.ok();
    }

    @GetMapping("/one/{id}")
    public Result<WmNewsDtoSave> getById(@PathVariable(name = "id") Integer id) {
        WmNewsDtoSave wmNewsDtoSave = wmNewsService.getDtoById(id);
        return Result.ok(wmNewsDtoSave);
    }

    @PostMapping("/vo/search")
    public Result<PageInfo<WmNewsVo>> searchByCondition(@RequestBody PageRequestDto<WmNews> requestDto) {
        PageInfo<WmNewsVo> pageInfo = wmNewsService.searchByCondition(requestDto);
        return Result.ok(pageInfo);
    }


    @PutMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable(name = "id") Integer id,
                               @PathVariable(name = "status") Integer status) {

        if (id == null) {
            return Result.errorMessage("状态参数错误");
        }
        if (status == 8 || status == 2) {

            WmNews record = new WmNews();
            record.setId(id);
            record.setStatus(status);
            wmNewsService.updateById(record);
            return Result.ok();
        }else{
            return Result.errorMessage("错误");
        }

    }

    //上架 和下架  1  service中 调用 mapper update wm_news set enable=1 where id=?
    //   2.远程feign调用文章微服务  将状态值 给文章微服务 更新自己的表 和eanble的值进行同步。

    @GetMapping("/list/{status}")
    public List<WmNews> findByStatus(@PathVariable(name = "status") Integer status) {
        //select * from biao where status = ?
        QueryWrapper<WmNews> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("status",status);
        return wmNewsService.list(queryWrapper);
    }
}

