package com.zdpx.weekly.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.omg.CORBA.IDLType;

/**
 * 周报项目表 实体对象
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("WEEK_PROJECT")
@ApiModel(value = "WeekProject对象", description = "周报项目表")
public class WeekProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目周报ID")
    @TableId(value = "WEEK_PROJECT_ID",type = IdType.ASSIGN_ID)
    private String weekProjectId;

    @ApiModelProperty(value = "周报ID")
    @TableField("WEEK_ID")
    private String weekId;

    @ApiModelProperty(value = "项目ID")
    @TableField("PROJECT_ID")
    private String projectId;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECT_NAME")
    private String projectName;

    @ApiModelProperty(value = "项目周报内容")
    @TableField("PROJECT_CONTENT")
    private String projectContent;


}
