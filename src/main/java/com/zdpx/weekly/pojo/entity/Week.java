package com.zdpx.weekly.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 周报表 实体对象
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("WEEK")
@ApiModel(value = "Week对象", description = "周报表")
public class Week implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "周报主键")
    @TableId(value = "WEEK_ID")
    private String weekId;

    @ApiModelProperty(value = "提交人ID")
    @TableField("USER_ID")
    private String userId;

    @ApiModelProperty(value = "提交人姓名")
    @TableField("USER_NAME")
    private String userName;

    @ApiModelProperty(value = "提交人部门")
    @TableField("USER_DEPT")
    private String userDept;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "内容")
    @TableField("CONTENT")
    private String content;

    @ApiModelProperty(value = "可见类型：0：对所属父节点人员可见；1：所有人可见；2：对指定人员可见")
    @TableField("VISIBLE_TYPE")
    private Integer visibleType;


}
