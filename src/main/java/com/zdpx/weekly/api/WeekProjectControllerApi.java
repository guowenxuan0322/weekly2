package com.zdpx.weekly.api;

import com.zdpx.pxframework.core.vo.PageResult;
import com.zdpx.pxframework.core.vo.Result;
import com.zdpx.weekly.pojo.vo.WeekProjectVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.zdpx.weekly.pojo.entity.WeekProject;

import java.util.List;

import com.zdpx.weekly.pojo.query.WeekProjectQuery;


/**
 * 周报项目 接口Swagger Api
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Api(tags = "周报项目模块", description = "实现对周报项目数据的基本操作")
public interface WeekProjectControllerApi {


    /**
     * 按照条件查询周报项目信息
     *
     * @param weekProjectQuery 查询条件
     * @return 结果信息
     */
    @ApiOperation(value = "按照条件查询周报项目信息")
    Result<List<WeekProject>> list(WeekProjectQuery weekProjectQuery);

    /**
     * 分页及条件查询周报项目信息
     *
     * @param page             页码
     * @param size             分页大小
     * @param weekProjectQuery 查询条件
     * @return 分页结果信息
     */
    @ApiOperation(value = "分页及条件查询周报项目信息")
    Result<PageResult<WeekProject>> search(@ApiParam(name = "page", value = "当前页", required = true, type = "path") Long page,
                                           @ApiParam(name = "size", value = "页码大小", required = true, type = "path") Long size,
                                           WeekProjectQuery weekProjectQuery);

    /**
     * 根据项目-分页及条件查询周报项目信息
     *
     * @param page             页码
     * @param size             分页大小
     * @param weekProjectQuery 查询条件
     * @return 分页结果信息
     */
    @ApiOperation(value = "根据项目-分页及条件查询周报项目信息")
    Result<PageResult<WeekProjectVo>> searchByProject(@ApiParam(name = "page", value = "当前页", required = true, type = "path") Long page,
                                                      @ApiParam(name = "size", value = "页码大小", required = true, type = "path") Long size,
                                                      WeekProjectQuery weekProjectQuery);

    /**
     * 根据ID获取周报项目详情
     *
     * @param id 周报项目ID
     * @return 周报项目详细信息
     */
    @ApiOperation(value = "获取单个周报项目详情")
    @ApiParam(name = "id", value = "周报项目ID", required = true, type = "path")
    Result<WeekProject> detail(String id);

    /**
     * 增加周报项目信息
     *
     * @param weekProject 周报项目增加实体
     * @return 增加后的周报项目信息
     */
    @ApiOperation(value = "增加周报项目信息")
    Result<WeekProject> add(WeekProject weekProject);

    /**
     * 修改周报项目信息
     *
     * @param id          周报项目ID
     * @param weekProject 周报项目修改实体
     * @return 修改后的周报项目信息
     */
    @ApiOperation(value = "修改周报项目信息")
    Result<WeekProject> updateById(@ApiParam(name = "id", value = "周报项目ID", type = "path") String id, WeekProject weekProject);

    /**
     * 删除周报项目信息
     *
     * @param id 周报项目ID
     * @return 删除结果信息
     */
    @ApiOperation(value = "删除单个周报项目信息")
    Result<Boolean> deleteById(@ApiParam(name = "id", value = "周报项目ID", type = "path") String id);

    /**
     * 删除周报项目信息
     *
     * @param idList 周报项目ID数组
     * @return 删除结果信息
     */
    @ApiOperation(value = "批量删除周报项目信息")
    Result<Boolean> batchRemove(List<String> idList);

}
