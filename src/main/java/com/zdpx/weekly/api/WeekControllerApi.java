package com.zdpx.weekly.api;

import com.zdpx.pxframework.core.vo.PageResult;
import com.zdpx.pxframework.core.vo.Result;
import com.zdpx.weekly.pojo.vo.WeekVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.zdpx.weekly.pojo.entity.Week;

import java.util.List;

import com.zdpx.weekly.pojo.query.WeekQuery;


/**
 * 周报 接口Swagger Api
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Api(tags = "周报模块", description = "实现对周报数据的基本操作")
public interface WeekControllerApi {


    /**
     * 按照条件查询周报信息
     *
     * @param weekQuery 查询条件
     * @return 结果信息
     */
    @ApiOperation(value = "按照条件查询周报信息")
    Result<List<Week>> list(WeekQuery weekQuery);

    /**
     * 本人周报，分页及条件查询周报信息
     *
     * @param page      页码
     * @param size      分页大小
     * @param weekQuery 查询条件
     * @return 分页结果信息
     */
    @ApiOperation(value = "本人周报-分页及条件查询周报信息")
    Result<PageResult<WeekVo>> searchSelf(@ApiParam(name = "page", value = "当前页", required = true, type = "path") Long page,
                                          @ApiParam(name = "size", value = "页码大小", required = true, type = "path") Long size,
                                          WeekQuery weekQuery);

    /**
     * 可见周报，分页及条件查询周报信息
     *
     * @param page      页码
     * @param size      分页大小
     * @param weekQuery 查询条件
     * @return 分页结果信息
     */
    @ApiOperation(value = "可见周报-分页及条件查询周报信息")
    Result<PageResult<WeekVo>> searchVisible(@ApiParam(name = "page", value = "当前页", required = true, type = "path") Long page,
                                          @ApiParam(name = "size", value = "页码大小", required = true, type = "path") Long size,
                                          WeekQuery weekQuery);

    /**
     * 根据ID获取周报详情
     *
     * @param id 周报ID
     * @return 周报详细信息
     */
    @ApiOperation(value = "获取单个周报详情")
    @ApiParam(name = "id", value = "周报ID", required = true, type = "path")
    Result<Week> detail(String id);

    /**
     * 增加周报信息
     *
     * @param weekVO 周报增加实体
     * @return 增加后的周报信息
     */
    @ApiOperation(value = "增加周报信息")
    Result<WeekVo> add(WeekVo weekVO);

    /**
     * 修改周报信息
     *
     * @param id   周报ID
     * @param weekVo 周报修改实体
     * @return 修改后的周报信息
     */
    @ApiOperation(value = "修改周报信息")
    Result<Week> updateById(@ApiParam(name = "id", value = "周报ID", type = "path") String id, WeekVo weekVo);

    /**
     * 删除周报信息
     *
     * @param id 周报ID
     * @return 删除结果信息
     */
    @ApiOperation(value = "删除单个周报信息")
    Result<Boolean> deleteById(@ApiParam(name = "id", value = "周报ID", type = "path") String id);

    /**
     * 删除周报信息
     *
     * @param idList 周报ID数组
     * @return 删除结果信息
     */
    @ApiOperation(value = "批量删除周报信息")
    Result<Boolean> batchRemove(List<String> idList);

}
