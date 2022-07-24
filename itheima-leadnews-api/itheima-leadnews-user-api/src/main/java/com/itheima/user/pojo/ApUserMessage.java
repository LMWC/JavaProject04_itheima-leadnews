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
 * APP用户消息通知信息表
 * </p>
 *
 * @author ljh
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_user_message")
@ApiModel(value="ApUserMessage", description="APP用户消息通知信息表")
public class ApUserMessage implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableField("id")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "发送人ID")
    @TableField("sender_id")
    private Integer senderId;

    @ApiModelProperty(value = "发送人昵称")
    @TableField("sender_name")
    private String senderName;

    @ApiModelProperty(value = "私信内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "消息类型	            0 关注	            1 取消关注	            2 点赞文章	            3 取消点赞文章	            4 转发文章	            5 收藏文章	            6 点赞评论	            7 审核通过评论	            8 私信通知	            9 评论通知	            10 分享通知	            	            100 身份证审核通过	            101 身份证审核拒绝	            102 实名认证通过	            103 实名认证失败	            104 自媒体人祝贺	            105 异常登录通知	            106 反馈回复	            107 转发通知")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "是否阅读")
    @TableField("is_read")
    private Integer isRead;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "阅读时间")
    @TableField("read_time")
    private LocalDateTime readTime;


}
