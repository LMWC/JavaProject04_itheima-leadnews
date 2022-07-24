package com.itheima.media.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 自媒体图文内容信息表
 * </p>
 *
 * @author ljh
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wm_news")
@ApiModel(value="WmNews", description="自媒体图文内容信息表")
public class WmNews implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "自媒体用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "图文内容[{}]方式存储")
    @TableField("content")
    private String content;//List<>

    @ApiModelProperty(value = " 0 无图文章 1 单图文章 3 多图文章")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "图文频道ID")
    @TableField("channel_id")
    private Integer channelId;

    @TableField("labels")
    private String labels;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "提交时间")
    @TableField("submited_time")
    private LocalDateTime submitedTime;

    @ApiModelProperty(value = "0草稿	 1提交（待审核）2 审核失败	 3 人工审核中	 	 8 审核通过（待发布）9 已发布")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "定时发布时间，不定时则为空")
    @TableField("publish_time")
    private LocalDateTime publishTime;

    @ApiModelProperty(value = "拒绝理由")
    @TableField("reason")
    private String reason;

    @ApiModelProperty(value = "发布库文章ID")
    @TableField("article_id")
    private Long articleId;

    @ApiModelProperty(value = "封面图片（用逗号分隔）")
    @TableField("images")
    private String images;

    @ApiModelProperty(value = "1 上架 0 下架")
    @TableField("enable")
    private Integer enable;


}
