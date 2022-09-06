package com.zdpx.weekly.pojo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class WeekProjectVo implements Serializable {
    /**
     * 周报表字段
     */
    @ApiModelProperty(value = "周报主键")
    private String weekId;
    @ApiModelProperty(value = "提交人ID")
    private String userId;
    @ApiModelProperty(value = "提交人姓名")
    private String userName;
    @ApiModelProperty(value = "提交人部门")
    private String userDept;
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") //返回时间类型
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") //接收时间类型
    private Date createTime;
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") //返回时间类型
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") //接收时间类型
    private Date updateTime;
    /**
     * 项目周报表字段
     */
    @ApiModelProperty(value = "项目周报ID")
    private String weekProjectId;
    @ApiModelProperty(value = "项目ID")
    private String projectId;
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    @ApiModelProperty(value = "项目周报内容")
    private String projectContent;
}
