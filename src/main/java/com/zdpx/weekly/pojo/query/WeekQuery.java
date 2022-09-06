package com.zdpx.weekly.pojo.query;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * 周报信息 查询对象
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "周报条件查询对象", description = "用于条件查询使用")
public class WeekQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "周报主键")
    private String weekId;
    @ApiModelProperty(value = "提交人ID")
    private String userId;
    @ApiModelProperty(value = "提交人姓名")
    private String userName;
    @ApiModelProperty(value = "提交人部门")
    private String userDept;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "可见类型：0：对所属父节点人员可见；1：所有人可见；2：对指定人员可见")
    private Integer visibleType;
    @ApiModelProperty(value = "查询开始时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") //返回时间类型
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") //接收时间类型
    private Date startTime;
    @ApiModelProperty(value = "查询结束时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") //返回时间类型
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") //接收时间类型
    private Date endTime;

//    @ApiModelProperty(value = "查询类型：currentWeek:本周,all:所有时间点")
//    private String selectType;
}
