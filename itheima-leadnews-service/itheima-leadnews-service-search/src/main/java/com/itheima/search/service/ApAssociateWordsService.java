package com.itheima.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.search.dto.SearchDto;
import com.itheima.search.pojo.ApAssociateWords;

import java.util.List;

/**
 * <p>
 * 联想词表 服务类
 * </p>
 *
 * @author ljh
 * @since 2022-08-04
 */
public interface ApAssociateWordsService extends IService<ApAssociateWords> {


    List<String> searchTen(SearchDto searchDto);
}
