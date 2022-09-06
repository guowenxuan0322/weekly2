package com.zdpx.weekly.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zdpx.pxframework.core.validator.group.AddGroup;
import com.zdpx.pxframework.core.validator.group.UpdateGroup;
import com.zdpx.pxframework.core.vo.PageResult;
import com.zdpx.pxframework.core.vo.Result;
import com.zdpx.weekly.service.WeekCommentService;
import com.zdpx.weekly.api.WeekCommentControllerApi;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.zdpx.weekly.pojo.entity.WeekComment;
import com.zdpx.weekly.pojo.query.WeekCommentQuery;

import javax.annotation.Resource;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;


/**
 * 周报评论 接口控制器
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@RestController
@RequestMapping("/week-comment")
public class WeekCommentController implements WeekCommentControllerApi {
    @Resource
    private WeekCommentService weekCommentService;


    /**
     * 获取条件查询信息
     *
     * @param weekCommentQuery 查询条件
     * @return 查询封装
     */
    private LambdaQueryWrapper<WeekComment> getWeekCommentQueryWrapper(WeekCommentQuery weekCommentQuery) {
        LambdaQueryWrapper<WeekComment> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(weekCommentQuery.getWeekId())) {
            wrapper.like(WeekComment::getWeekId, weekCommentQuery.getWeekId());
        }
        if (!StringUtils.isEmpty(weekCommentQuery.getContent())) {
            wrapper.like(WeekComment::getContent, weekCommentQuery.getContent());
        }
        if (!StringUtils.isEmpty(weekCommentQuery.getUserId())) {
            wrapper.like(WeekComment::getUserId, weekCommentQuery.getUserId());
        }
        if (!StringUtils.isEmpty(weekCommentQuery.getUserName())) {
            wrapper.like(WeekComment::getUserName, weekCommentQuery.getUserName());
        }
        return wrapper;
    }

    /**
     * 按照条件查询周报评论信息
     *
     * @param weekCommentQuery 查询条件
     * @return 条件结果信息
     */
    @Override
    @GetMapping
    public Result<List<WeekComment>> list(WeekCommentQuery weekCommentQuery) {
        return Result.success(weekCommentService.list(getWeekCommentQueryWrapper(weekCommentQuery)));
    }

    /**
     * 分页及条件查询周报评论信息
     *
     * @param size             分页大小
     * @param page             页码
     * @param weekCommentQuery 查询条件
     * @return 分页结果信息
     */
    @Override
    @GetMapping("/{size}/{page}")
    public Result<PageResult<WeekComment>> search(@PathVariable("size") Long size, @PathVariable("page") Long page, WeekCommentQuery weekCommentQuery) {
        if (page <= 0) {
            page = 1L;
        }
        if (size <= 0) {
            size = 10L;
        }
        Page<WeekComment> pageParam = new Page<>(page, size);

        weekCommentService.page(pageParam, getWeekCommentQueryWrapper(weekCommentQuery));
        PageResult<WeekComment> pageResult = new PageResult<WeekComment>(pageParam.getTotal(), pageParam.getSize(), pageParam.getCurrent(), pageParam.getRecords());

        return Result.success(pageResult);
    }

    /**
     * 根据ID获取周报评论详情
     *
     * @param id 周报评论ID
     * @return 周报评论详细信息
     */
    @Override
    @GetMapping("/{id}")
    public Result<WeekComment> detail(@PathVariable("id") String id) {
        return Result.success(weekCommentService.detailById(id));
    }

    /**
     * 增加周报评论信息
     *
     * @param weekComment 周报评论增加实体
     * @return 增加后的周报评论信息
     */
    @Override
    @PostMapping
    public Result<WeekComment> add(@RequestBody @Validated(AddGroup.class) WeekComment weekComment) {
        weekCommentService.saveWeekComment(weekComment);
        return Result.success(weekComment);
    }

    /**
     * 修改周报评论信息
     *
     * @param id          周报评论ID
     * @param weekComment 周报评论修改实体
     * @return 修改后的周报评论信息
     */
    @Override
    @PutMapping("/{id}")
    public Result<WeekComment> updateById(@PathVariable("id") String id, @RequestBody @Validated(UpdateGroup.class) WeekComment weekComment) {
        weekComment.setId(id);
        weekCommentService.updateWeekCommentById(weekComment);
        return Result.success(weekCommentService.getById(id));
    }


    /**
     * 删除周报评论信息
     *
     * @param id 周报评论ID
     * @return 删除结果
     */
    @Override
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") String id) {
        weekCommentService.deleteById(id);
        return Result.success(true);
    }

    /**
     * 删除周报评论信息
     *
     * @param idList 周报评论ID数组
     * @return 删除结果
     */
    @Override
    @DeleteMapping("/batch-remove")
    public Result<Boolean> batchRemove(@RequestBody List<String> idList) {
        weekCommentService.removeByIds(idList);
        return Result.success(true);
    }
}
