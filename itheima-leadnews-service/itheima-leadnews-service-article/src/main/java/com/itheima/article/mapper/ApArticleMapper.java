package com.itheima.article.mapper;

import com.itheima.article.pojo.ApArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 文章信息表，存储已发布的文章 Mapper 接口
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
public interface ApArticleMapper extends BaseMapper<ApArticle> {

    List<ApArticle> pageByOrder(@Param("channelId") Integer channelId,
                                @Param("start") Long start,
                                @Param("size") Long size);

    Long selectArticleCount(@Param("channelId")Integer channelId);


    @Select(value="select apa.* from ap_article apa left join ap_article_config aac on apa.id = aac.article_id\n" +
            "where  aac.is_delete=0 and aac.is_down=0  and publish_time> #{startTime}")
    List<ApArticle> selectRedisVoList(LocalDateTime startTime);

}
