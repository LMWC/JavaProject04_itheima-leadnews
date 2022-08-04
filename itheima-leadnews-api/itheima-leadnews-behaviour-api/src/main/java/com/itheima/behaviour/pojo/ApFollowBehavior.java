package com.itheima.behaviour.pojo;

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
 * APP关注行为表
 * </p>
 *
 * @author ljh
 * @since 2022-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_follow_behavior")
@ApiModel(value="ApFollowBehavior", description="APP关注行为表")
public class ApFollowBehavior implements Serializable {


    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "实体ID")
    @TableField("entry_id")
    private Integer entryId;

    @ApiModelProperty(value = "文章ID")
    @TableField("article_id")
    private Long articleId;

    @ApiModelProperty(value = "关注用户ID")
    @TableField("follow_id")
    private Integer followId;

    @ApiModelProperty(value = "登录时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
