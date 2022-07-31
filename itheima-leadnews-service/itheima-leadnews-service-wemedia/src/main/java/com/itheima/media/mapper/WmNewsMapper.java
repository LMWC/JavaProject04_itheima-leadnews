package com.itheima.media.mapper;

import com.itheima.media.pojo.WmNews;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.media.vo.WmNewsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 自媒体图文内容信息表 Mapper 接口
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
public interface WmNewsMapper extends BaseMapper<WmNews> {


    /**
     * 1.映射文件的名称和接口的名称一致 并在同一个目录下 (不一一定要这样 可以通过配置自定义路径)
     * 2.映射文件的namespace 和接口的全路径一致
     * 3.映射文件id 和方法名一致
     * 4 映射文件的parametertype 和 方法的参数类型一致
     * 5 映射文件的resultType 和方法的返回值一致
     *
     *
     *
     */

    
    List<WmNewsVo> selectMyPage(@Param("title") String title,
                                @Param("start")Long start,
                                @Param("size")Long size);

    Long selectMyCount(@Param("title") String title);
}
