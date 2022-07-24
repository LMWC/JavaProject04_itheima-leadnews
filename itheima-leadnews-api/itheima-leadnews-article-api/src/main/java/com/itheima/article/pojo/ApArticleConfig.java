package com.itheima.article.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * APP已发布文章配置表
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_article_config")
@ApiModel(value="ApArticleConfig", description="APP已发布文章配置表")
public class ApArticleConfig implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "文章ID")
    @TableField("article_id")
    private Long articleId;

    @ApiModelProperty(value = "是否可评论")
    @TableField("is_comment")
    private Integer isComment;

    @ApiModelProperty(value = "是否转发")
    @TableField("is_forward")
    private Integer isForward;

    @ApiModelProperty(value = "是否下架 1 标识已经下架 0 标识没有下架")
    @TableField("is_down")
    private Integer isDown;

    @ApiModelProperty(value = "是否已删除")
    @TableField("is_delete")
    private Integer isDelete;


}
