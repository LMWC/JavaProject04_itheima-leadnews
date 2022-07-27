package com.itheima.media.controller;


import com.itheima.common.pojo.PageInfo;
import com.itheima.common.pojo.PageRequestDto;
import com.itheima.common.pojo.Result;
import com.itheima.media.dto.WmNewsDto;
import com.itheima.media.dto.WmNewsDtoSave;
import com.itheima.media.pojo.WmNews;
import com.itheima.media.service.WmNewsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import com.itheima.core.controller.AbstractCoreController;

/**
* <p>
* 自媒体图文内容信息表 控制器</p>
* @author ljh
* @since 2021-12-22
*/
@Api(value="自媒体图文内容信息表",tags = "WmNewsController")
@RestController
@RequestMapping("/wmNews")
public class WmNewsController extends AbstractCoreController<WmNews> {

    private WmNewsService wmNewsService;

    //注入
    @Autowired
    public WmNewsController(WmNewsService wmNewsService) {
        super(wmNewsService);
        this.wmNewsService=wmNewsService;
    }

    @PostMapping("/searchPage")
    public Result<PageInfo<WmNews>> findByPageDto(@RequestBody PageRequestDto<WmNewsDto> pageRequestDto){

        PageInfo<WmNews> pageInfo =   wmNewsService.findByPageDto(pageRequestDto);
        return Result.ok(pageInfo);
    }

    /**
     * 发表文章
     * @param isSubmit  0 /1
     * @param wmNewsDtoSave
     * @return
     */
    @PostMapping("/save/{isSubmit}")
    public Result save(@PathVariable(name="isSubmit") Integer isSubmit,
                       @RequestBody WmNewsDtoSave wmNewsDtoSave) {
        if(isSubmit>1 || isSubmit<0){
            return Result.errorMessage("你传递的参数类型有误");
        }
        wmNewsService.save(wmNewsDtoSave,isSubmit);
        return Result.ok();
    }

    @GetMapping("/one/{id}")
    public Result<WmNewsDtoSave> getById(@PathVariable(name="id")Integer id){
        WmNewsDtoSave wmNewsDtoSave = wmNewsService.getDtoById(id);
        return Result.ok(wmNewsDtoSave);
    }
}

