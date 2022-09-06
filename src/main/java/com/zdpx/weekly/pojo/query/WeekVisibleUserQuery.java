package com.zdpx.weekly.pojo.query;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * 周报可见人员信息 查询对象
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "周报可见人员条件查询对象", description = "用于条件查询使用")
public class WeekVisibleUserQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "周报ID")
    private String weekId;
    @ApiModelProperty(value = "可见人员ID")
    private String visibleUserId;


}
