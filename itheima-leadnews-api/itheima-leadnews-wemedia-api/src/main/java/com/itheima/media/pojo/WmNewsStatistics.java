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
import java.time.LocalDate;

/**
 * <p>
 * 自媒体图文数据统计表
 * </p>
 *
 * @author ljh
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wm_news_statistics")
@ApiModel(value="WmNewsStatistics", description="自媒体图文数据统计表")
public class WmNewsStatistics implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "主账号ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "子账号ID")
    @TableField("article")
    private Integer article;

    @ApiModelProperty(value = "阅读量")
    @TableField("read_count")
    private Integer readCount;

    @ApiModelProperty(value = "评论量")
    @TableField("comment")
    private Integer comment;

    @ApiModelProperty(value = "关注量")
    @TableField("follow")
    private Integer follow;

    @ApiModelProperty(value = "收藏量")
    @TableField("collection")
    private Integer collection;

    @ApiModelProperty(value = "转发量")
    @TableField("forward")
    private Integer forward;

    @ApiModelProperty(value = "点赞量")
    @TableField("likes")
    private Integer likes;

    @ApiModelProperty(value = "不喜欢")
    @TableField("unlikes")
    private Integer unlikes;

    @ApiModelProperty(value = "取消关注量")
    @TableField("unfollow")
    private Integer unfollow;

    @TableField("burst")
    private String burst;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDate createdTime;


}
