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
 * 自媒体粉丝数据统计表
 * </p>
 *
 * @author ljh
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wm_fans_statistics")
@ApiModel(value="WmFansStatistics", description="自媒体粉丝数据统计表")
public class WmFansStatistics implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "主账号ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "子账号ID")
    @TableField("article")
    private Integer article;

    @TableField("read_count")
    private Integer readCount;

    @TableField("comment")
    private Integer comment;

    @TableField("follow")
    private Integer follow;

    @TableField("collection")
    private Integer collection;

    @TableField("forward")
    private Integer forward;

    @TableField("likes")
    private Integer likes;

    @TableField("unlikes")
    private Integer unlikes;

    @TableField("unfollow")
    private Integer unfollow;

    @TableField("burst")
    private String burst;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDate createdTime;


}
