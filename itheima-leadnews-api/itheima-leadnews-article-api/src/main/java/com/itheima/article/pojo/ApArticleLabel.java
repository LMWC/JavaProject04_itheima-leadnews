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
 * 文章标签信息表
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_article_label")
@ApiModel(value="ApArticleLabel", description="文章标签信息表")
public class ApArticleLabel implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("article_id")
    private Long articleId;

    @ApiModelProperty(value = "标签ID")
    @TableField("label_id")
    private Integer labelId;

    @ApiModelProperty(value = "排序")
    @TableField("count")
    private Integer count;


}
