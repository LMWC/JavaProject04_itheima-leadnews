package com.itheima.article.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * APP用户动态信息表
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_dynamic")
@ApiModel(value="ApDynamic", description="APP用户动态信息表")
public class ApDynamic implements Serializable {


    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "文章作者的ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "作者昵称")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "频道名称")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "是否转发")
    @TableField("is_forward")
    private Integer isForward;

    @ApiModelProperty(value = "转发文章ID")
    @TableField("article_id")
    private Long articleId;

    @ApiModelProperty(value = "转发文章标题")
    @TableField("articel_title")
    private String articelTitle;

    @ApiModelProperty(value = "转发文章图片")
    @TableField("article_image")
    private String articleImage;

    @ApiModelProperty(value = "布局标识	            0 无图动态	            1 单图动态	            2 多图动态	            3 转发动态")
    @TableField("layout")
    private Integer layout;

    @ApiModelProperty(value = "文章图片	            多张逗号分隔")
    @TableField("images")
    private String images;

    @ApiModelProperty(value = "点赞数量")
    @TableField("likes")
    private Integer likes;

    @ApiModelProperty(value = "收藏数量")
    @TableField("collection")
    private Integer collection;

    @ApiModelProperty(value = "评论数量")
    @TableField("comment")
    private Integer comment;

    @ApiModelProperty(value = "阅读数量")
    @TableField("views")
    private Integer views;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
