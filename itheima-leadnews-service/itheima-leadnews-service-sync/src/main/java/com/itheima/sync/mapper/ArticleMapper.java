package com.itheima.sync.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.article.pojo.ApArticle;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleMapper extends BaseMapper<ApArticle> {
    @Select(value="select count(*) from ap_article")
    Long selectCount();

    @Select(value="select * from ap_article limit #{start},#{size}")
    List<ApArticle> selectByPage(@Param(value = "start") Long start,
                                 @Param(value="size") Long size);

    @Select(value="select  apa.* from ap_article apa left join ap_article_config apc on apa.id=apc.article_id\n" +
            "where apc.is_delete=0 and apc.is_down=0  and publish_time > #{publishTime}")
    List<ApArticle> selectForCondition(@Param(value="publishTime") LocalDateTime publishTime);
}
