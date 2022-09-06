package com.zdpx.weekly.pojo.query;

import java.util.Date;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * 项目信息 查询对象
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "项目条件查询对象", description = "用于条件查询使用")
public class ProjectQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "项目ID")
    private String projectId;
    @ApiModelProperty(value = "序号")
    private String serialNum;
    @ApiModelProperty(value = "项目编码")
    private String projectCode;
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    @ApiModelProperty(value = "预计结束时间")
    private Date planStopTime;
    @ApiModelProperty(value = "描述")
    private String describe;


}
