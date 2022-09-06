package com.zdpx.weekly.api;

import com.zdpx.pxframework.core.vo.PageResult;
import com.zdpx.pxframework.core.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.zdpx.weekly.pojo.entity.Project;

import java.util.List;

import com.zdpx.weekly.pojo.query.ProjectQuery;


/**
 * 项目 接口Swagger Api
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Api(tags = "项目模块", description = "实现对项目数据的基本操作")
public interface ProjectControllerApi {


    /**
     * 按照条件查询项目信息
     *
     * @param projectQuery 查询条件
     * @return 结果信息
     */
    @ApiOperation(value = "按照条件查询项目信息")
    Result<List<Project>> list(ProjectQuery projectQuery);

    /**
     * 分页及条件查询项目信息
     *
     * @param page         页码
     * @param size         分页大小
     * @param projectQuery 查询条件
     * @return 分页结果信息
     */
    @ApiOperation(value = "分页及条件查询项目信息")
    Result<PageResult<Project>> search(@ApiParam(name = "page", value = "当前页", required = true, type = "path") Long page,
                                       @ApiParam(name = "size", value = "页码大小", required = true, type = "path") Long size,
                                       ProjectQuery projectQuery);

    /**
     * 根据ID获取项目详情
     *
     * @param id 项目ID
     * @return 项目详细信息
     */
    @ApiOperation(value = "获取单个项目详情")
    @ApiParam(name = "id", value = "项目ID", required = true, type = "path")
    Result<Project> detail(String id);

    /**
     * 增加项目信息
     *
     * @param project 项目增加实体
     * @return 增加后的项目信息
     */
    @ApiOperation(value = "增加项目信息")
    Result<Project> add(Project project);

    /**
     * 修改项目信息
     *
     * @param id      项目ID
     * @param project 项目修改实体
     * @return 修改后的项目信息
     */
    @ApiOperation(value = "修改项目信息")
    Result<Project> updateById(@ApiParam(name = "id", value = "项目ID", type = "path") String id, Project project);

    /**
     * 删除项目信息
     *
     * @param id 项目ID
     * @return 删除结果信息
     */
    @ApiOperation(value = "删除单个项目信息")
    Result<Boolean> deleteById(@ApiParam(name = "id", value = "项目ID", type = "path") String id);

    /**
     * 删除项目信息
     *
     * @param idList 项目ID数组
     * @return 删除结果信息
     */
    @ApiOperation(value = "批量删除项目信息")
    Result<Boolean> batchRemove(List<String> idList);

}
