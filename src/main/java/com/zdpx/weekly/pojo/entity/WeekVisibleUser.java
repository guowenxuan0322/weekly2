package com.zdpx.weekly.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 周报可见人员表 实体对象
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("WEEK_VISIBLE_USER")
@ApiModel(value = "WeekVisibleUser对象", description = "周报可见人员表")
public class WeekVisibleUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID")
    private String id;

    @ApiModelProperty(value = "周报ID")
    @TableField("WEEK_ID")
    private String weekId;

    @ApiModelProperty(value = "可见人员ID")
    @TableField("VISIBLE_USER_ID")
    private String visibleUserId;


}
