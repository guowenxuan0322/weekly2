package com.zdpx.weekly.api;

import com.zdpx.pxframework.core.vo.PageResult;
import com.zdpx.pxframework.core.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.zdpx.weekly.pojo.entity.WeekVisibleUser;

import java.util.List;

import com.zdpx.weekly.pojo.query.WeekVisibleUserQuery;


/**
 * 周报可见人员 接口Swagger Api
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Api(tags = "周报可见人员模块", description = "实现对周报可见人员数据的基本操作")
public interface WeekVisibleUserControllerApi {


    /**
     * 按照条件查询周报可见人员信息
     *
     * @param weekVisibleUserQuery 查询条件
     * @return 结果信息
     */
    @ApiOperation(value = "按照条件查询周报可见人员信息")
    Result<List<WeekVisibleUser>> list(WeekVisibleUserQuery weekVisibleUserQuery);

    /**
     * 分页及条件查询周报可见人员信息
     *
     * @param page                 页码
     * @param size                 分页大小
     * @param weekVisibleUserQuery 查询条件
     * @return 分页结果信息
     */
    @ApiOperation(value = "分页及条件查询周报可见人员信息")
    Result<PageResult<WeekVisibleUser>> search(@ApiParam(name = "page", value = "当前页", required = true, type = "path") Long page,
                                               @ApiParam(name = "size", value = "页码大小", required = true, type = "path") Long size,
                                               WeekVisibleUserQuery weekVisibleUserQuery);

    /**
     * 根据ID获取周报可见人员详情
     *
     * @param id 周报可见人员ID
     * @return 周报可见人员详细信息
     */
    @ApiOperation(value = "获取单个周报可见人员详情")
    @ApiParam(name = "id", value = "周报可见人员ID", required = true, type = "path")
    Result<WeekVisibleUser> detail(String id);

    /**
     * 增加周报可见人员信息
     *
     * @param weekVisibleUser 周报可见人员增加实体
     * @return 增加后的周报可见人员信息
     */
    @ApiOperation(value = "增加周报可见人员信息")
    Result<WeekVisibleUser> add(WeekVisibleUser weekVisibleUser);

    /**
     * 修改周报可见人员信息
     *
     * @param id              周报可见人员ID
     * @param weekVisibleUser 周报可见人员修改实体
     * @return 修改后的周报可见人员信息
     */
    @ApiOperation(value = "修改周报可见人员信息")
    Result<WeekVisibleUser> updateById(@ApiParam(name = "id", value = "周报可见人员ID", type = "path") String id, WeekVisibleUser weekVisibleUser);

    /**
     * 删除周报可见人员信息
     *
     * @param id 周报可见人员ID
     * @return 删除结果信息
     */
    @ApiOperation(value = "删除单个周报可见人员信息")
    Result<Boolean> deleteById(@ApiParam(name = "id", value = "周报可见人员ID", type = "path") String id);

    /**
     * 删除周报可见人员信息
     *
     * @param idList 周报可见人员ID数组
     * @return 删除结果信息
     */
    @ApiOperation(value = "批量删除周报可见人员信息")
    Result<Boolean> batchRemove(List<String> idList);

}
