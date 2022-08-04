package com.itheima.search.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * APP用户搜索信息表
 * </p>
 *
 * @author ljh
 * @since 2022-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_user_search")
@ApiModel(value="ApUserSearch", description="APP用户搜索信息表")
public class ApUserSearch implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "行为实体ID")
    @TableField("entry_id")
    private Integer entryId;

    @ApiModelProperty(value = "搜索词")
    @TableField("keyword")
    private String keyword;

    @ApiModelProperty(value = "当前状态0 无效 1有效")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
