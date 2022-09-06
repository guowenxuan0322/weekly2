package com.zdpx.weekly.api;

import com.zdpx.pxframework.core.vo.PageResult;
import com.zdpx.pxframework.core.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.zdpx.weekly.pojo.entity.WeekComment;

import java.util.List;

import com.zdpx.weekly.pojo.query.WeekCommentQuery;


/**
 * 周报评论 接口Swagger Api
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Api(tags = "周报评论模块", description = "实现对周报评论数据的基本操作")
public interface WeekCommentControllerApi {


    /**
     * 按照条件查询周报评论信息
     *
     * @param weekCommentQuery 查询条件
     * @return 结果信息
     */
    @ApiOperation(value = "按照条件查询周报评论信息")
    Result<List<WeekComment>> list(WeekCommentQuery weekCommentQuery);

    /**
     * 分页及条件查询周报评论信息
     *
     * @param page             页码
     * @param size             分页大小
     * @param weekCommentQuery 查询条件
     * @return 分页结果信息
     */
    @ApiOperation(value = "分页及条件查询周报评论信息")
    Result<PageResult<WeekComment>> search(@ApiParam(name = "page", value = "当前页", required = true, type = "path") Long page,
                                           @ApiParam(name = "size", value = "页码大小", required = true, type = "path") Long size,
                                           WeekCommentQuery weekCommentQuery);

    /**
     * 根据ID获取周报评论详情
     *
     * @param id 周报评论ID
     * @return 周报评论详细信息
     */
    @ApiOperation(value = "获取单个周报评论详情")
    @ApiParam(name = "id", value = "周报评论ID", required = true, type = "path")
    Result<WeekComment> detail(String id);

    /**
     * 增加周报评论信息
     *
     * @param weekComment 周报评论增加实体
     * @return 增加后的周报评论信息
     */
    @ApiOperation(value = "增加周报评论信息")
    Result<WeekComment> add(WeekComment weekComment);

    /**
     * 修改周报评论信息
     *
     * @param id          周报评论ID
     * @param weekComment 周报评论修改实体
     * @return 修改后的周报评论信息
     */
    @ApiOperation(value = "修改周报评论信息")
    Result<WeekComment> updateById(@ApiParam(name = "id", value = "周报评论ID", type = "path") String id, WeekComment weekComment);

    /**
     * 删除周报评论信息
     *
     * @param id 周报评论ID
     * @return 删除结果信息
     */
    @ApiOperation(value = "删除单个周报评论信息")
    Result<Boolean> deleteById(@ApiParam(name = "id", value = "周报评论ID", type = "path") String id);

    /**
     * 删除周报评论信息
     *
     * @param idList 周报评论ID数组
     * @return 删除结果信息
     */
    @ApiOperation(value = "批量删除周报评论信息")
    Result<Boolean> batchRemove(List<String> idList);

}
