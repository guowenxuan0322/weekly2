package com.zdpx.weekly.pojo.query;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * 周报项目信息 查询对象
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "周报项目条件查询对象", description = "用于条件查询使用")
public class WeekProjectQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "项目周报ID")
    private String weekProjectId;
    @ApiModelProperty(value = "周报ID")
    private String weekId;
    @ApiModelProperty(value = "项目ID")
    private String projectId;
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    @ApiModelProperty(value = "项目周报内容")
    private String projectContent;
    @ApiModelProperty(value = "提交人ID")
    private String userId;
    @ApiModelProperty(value = "查询开始时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") //返回时间类型
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") //接收时间类型
    private Date startTime;
    @ApiModelProperty(value = "查询结束时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") //返回时间类型
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") //接收时间类型
    private Date endTime;


}
