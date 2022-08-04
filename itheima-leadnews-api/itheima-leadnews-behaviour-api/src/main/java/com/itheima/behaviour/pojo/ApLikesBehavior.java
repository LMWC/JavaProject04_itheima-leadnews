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
 * APP点赞行为表
 * </p>
 *
 * @author ljh
 * @since 2022-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_likes_behavior")
@ApiModel(value="ApLikesBehavior", description="APP点赞行为表")
public class ApLikesBehavior implements Serializable {


    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "实体ID")
    @TableField("entry_id")
    private Integer entryId;

    @ApiModelProperty(value = "文章ID")
    @TableField("article_id")
    private Long articleId;

    @ApiModelProperty(value = "点赞内容类型	            0文章	            1动态")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "1 点赞	           0 取消点赞")
    @TableField("operation")
    private Integer operation;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
