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
 * APP分享行为表
 * </p>
 *
 * @author ljh
 * @since 2022-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_share_behavior")
@ApiModel(value="ApShareBehavior", description="APP分享行为表")
public class ApShareBehavior implements Serializable {


    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "实体ID")
    @TableField("entry_id")
    private Integer entryId;

    @ApiModelProperty(value = "文章ID")
    @TableField("article_id")
    private Long articleId;

    @ApiModelProperty(value = "0 微信	            1 微信朋友圈	            2 QQ	            3 QQ 空间	            4 微博	            ")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "登录时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
