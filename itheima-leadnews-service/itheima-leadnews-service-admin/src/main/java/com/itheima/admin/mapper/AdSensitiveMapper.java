package com.itheima.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.admin.pojo.AdSensitive;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 敏感词信息表 Mapper 接口
 * </p>
 *
 * @author ljh
 * @since 2022-07-22
 */
public interface AdSensitiveMapper extends BaseMapper<AdSensitive> {

    @Select(value="select sensitives from ad_sensitive")
    List<String> selectAdSensitive();

}
