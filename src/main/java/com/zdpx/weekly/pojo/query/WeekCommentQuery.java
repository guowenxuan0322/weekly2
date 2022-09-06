package com.zdpx.weekly.pojo.query;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * 周报评论信息 查询对象
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "周报评论条件查询对象", description = "用于条件查询使用")
public class WeekCommentQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "周报ID")
    private String weekId;
    @ApiModelProperty(value = "评论内容")
    private String content;
    @ApiModelProperty(value = "评论ID")
    private String userId;
    @ApiModelProperty(value = "评论人姓名")
    private String userName;


}
