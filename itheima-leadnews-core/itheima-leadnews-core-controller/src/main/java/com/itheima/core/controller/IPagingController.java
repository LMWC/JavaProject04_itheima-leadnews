package com.itheima.core.controller;


import com.itheima.common.pojo.PageInfo;
import com.itheima.common.pojo.PageRequestDto;
import com.itheima.common.pojo.Result;


/**
 * 描述
 *
 * @author www.itheima.com
 * @version 1.0
 * @package com.changgou.core *
 * @since 1.0
 */
public interface IPagingController<T> {

    /**
     * 根据查询条件 requestDto 分页查询
     * @return
     */
    Result<PageInfo<T>> findByPage(PageRequestDto<T> requestDto);


}
