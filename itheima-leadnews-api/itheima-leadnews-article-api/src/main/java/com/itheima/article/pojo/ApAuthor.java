package com.itheima.article.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * APP文章作者信息表
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_author")
@ApiModel(value="ApAuthor", description="APP文章作者信息表")
public class ApAuthor implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "作者名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "0 爬取数据	            1 签约合作商	            2 平台自媒体人	            ")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "AP_USER 的ID值")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


    @ApiModelProperty(value = "自媒体账号")
    @TableField("wm_user_id")
    private Integer wmUserId;


}
