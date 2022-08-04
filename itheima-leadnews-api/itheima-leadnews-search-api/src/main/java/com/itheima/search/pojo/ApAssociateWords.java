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
 * 联想词表
 * </p>
 *
 * @author ljh
 * @since 2022-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_associate_words")
@ApiModel(value="ApAssociateWords", description="联想词表")
public class ApAssociateWords implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "联想词")
    @TableField("associate_words")
    private String associateWords;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
