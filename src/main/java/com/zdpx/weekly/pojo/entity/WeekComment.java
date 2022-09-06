package com.zdpx.weekly.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 周报评论表 实体对象
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("WEEK_COMMENT")
@ApiModel(value = "WeekComment对象", description = "周报评论表")
public class WeekComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableField("ID")
    private String id;

    @ApiModelProperty(value = "周报ID")
    @TableId(value = "WEEK_ID")
    private String weekId;

    @ApiModelProperty(value = "评论内容")
    @TableField("CONTENT")
    private String content;

    @ApiModelProperty(value = "评论ID")
    @TableField("USER_ID")
    private String userId;

    @ApiModelProperty(value = "评论人姓名")
    @TableField("USER_NAME")
    private String userName;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;


}
