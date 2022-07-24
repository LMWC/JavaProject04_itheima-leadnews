package com.itheima.article.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 热文章表
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_hot_articles")
@ApiModel(value="ApHotArticles", description="热文章表")
public class ApHotArticles implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("entry_id")
    private Integer entryId;

    @ApiModelProperty(value = "频道ID")
    @TableField("tag_id")
    private Integer tagId;

    @ApiModelProperty(value = "频道名称")
    @TableField("tag_name")
    private String tagName;

    @ApiModelProperty(value = "热度评分")
    @TableField("score")
    private Integer score;

    @ApiModelProperty(value = "文章ID")
    @TableField("article_id")
    private Long articleId;

    @ApiModelProperty(value = "省市")
    @TableField("province_id")
    private Integer provinceId;

    @ApiModelProperty(value = "市区")
    @TableField("city_id")
    private Integer cityId;

    @ApiModelProperty(value = "区县")
    @TableField("county_id")
    private Integer countyId;

    @ApiModelProperty(value = "是否阅读")
    @TableField("is_read")
    private Integer isRead;

    @TableField("release_date")
    private LocalDateTime releaseDate;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
