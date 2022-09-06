package com.zdpx.weekly.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 项目表 实体对象
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("PROJECT")
@ApiModel(value = "Project对象", description = "项目表")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID")
    @TableId(value = "PROJECT_ID")
    private String projectId;

    @ApiModelProperty(value = "序号")
    @TableField("SERIAL_NUM")
    private String serialNum;

    @ApiModelProperty(value = "项目编码")
    @TableField("PROJECT_CODE")
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECT_NAME")
    private String projectName;

    @ApiModelProperty(value = "预计结束时间")
    @TableField("PLAN_STOP_TIME")
    private Date planStopTime;

    @ApiModelProperty(value = "描述")
    @TableField("DESCRIBE")
    private String describe;


}
