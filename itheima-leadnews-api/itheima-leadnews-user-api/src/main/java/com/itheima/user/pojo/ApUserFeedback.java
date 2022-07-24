package com.itheima.user.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * APP用户反馈信息表
 * </p>
 *
 * @author ljh
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_user_feedback")
@ApiModel(value="ApUserFeedback", description="APP用户反馈信息表")
public class ApUserFeedback implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableField("id")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "发送人昵称")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "私信内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "反馈图片,多张逗号分隔")
    @TableField("images")
    private String images;

    @ApiModelProperty(value = "是否阅读")
    @TableField("is_solve")
    private Integer isSolve;

    @TableField("solve_note")
    private String solveNote;

    @ApiModelProperty(value = "阅读时间")
    @TableField("solved_time")
    private LocalDateTime solvedTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
