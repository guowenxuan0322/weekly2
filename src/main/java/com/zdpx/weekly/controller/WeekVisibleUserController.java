package com.zdpx.weekly.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zdpx.pxframework.core.validator.group.AddGroup;
import com.zdpx.pxframework.core.validator.group.UpdateGroup;
import com.zdpx.pxframework.core.vo.PageResult;
import com.zdpx.pxframework.core.vo.Result;
import com.zdpx.weekly.api.WeekVisibleUserControllerApi;
import com.zdpx.weekly.pojo.entity.WeekVisibleUser;
import com.zdpx.weekly.service.WeekVisibleUserService;
import com.zdpx.weekly.pojo.query.WeekVisibleUserQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 周报可见人员 接口控制器
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@RestController
@RequestMapping("/week-visible-user")
public class WeekVisibleUserController implements WeekVisibleUserControllerApi {
    @Resource
    private WeekVisibleUserService weekVisibleUserService;


    /**
     * 获取条件查询信息
     *
     * @param weekVisibleUserQuery 查询条件
     * @return 查询封装
     */
    private LambdaQueryWrapper<WeekVisibleUser> getWeekVisibleUserQueryWrapper(WeekVisibleUserQuery weekVisibleUserQuery) {
        LambdaQueryWrapper<WeekVisibleUser> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(weekVisibleUserQuery.getWeekId())) {
            wrapper.like(WeekVisibleUser::getWeekId, weekVisibleUserQuery.getWeekId());
        }
        if (!StringUtils.isEmpty(weekVisibleUserQuery.getVisibleUserId())) {
            wrapper.like(WeekVisibleUser::getVisibleUserId, weekVisibleUserQuery.getVisibleUserId());
        }
        return wrapper;
    }

    /**
     * 按照条件查询周报可见人员信息
     *
     * @param weekVisibleUserQuery 查询条件
     * @return 条件结果信息
     */
    @Override
    @GetMapping
    public Result<List<WeekVisibleUser>> list(WeekVisibleUserQuery weekVisibleUserQuery) {
        return Result.success(weekVisibleUserService.list(getWeekVisibleUserQueryWrapper(weekVisibleUserQuery)));
    }

    /**
     * 分页及条件查询周报可见人员信息
     *
     * @param size                 分页大小
     * @param page                 页码
     * @param weekVisibleUserQuery 查询条件
     * @return 分页结果信息
     */
    @Override
    @GetMapping("/{size}/{page}")
    public Result<PageResult<WeekVisibleUser>> search(@PathVariable("size") Long size, @PathVariable("page") Long page, WeekVisibleUserQuery weekVisibleUserQuery) {
        if (page <= 0) {
            page = 1L;
        }
        if (size <= 0) {
            size = 10L;
        }
        Page<WeekVisibleUser> pageParam = new Page<>(page, size);

        weekVisibleUserService.page(pageParam, getWeekVisibleUserQueryWrapper(weekVisibleUserQuery));
        PageResult<WeekVisibleUser> pageResult = new PageResult<WeekVisibleUser>(pageParam.getTotal(), pageParam.getSize(), pageParam.getCurrent(), pageParam.getRecords());

        return Result.success(pageResult);
    }

    /**
     * 根据ID获取周报可见人员详情
     *
     * @param id 周报可见人员ID
     * @return 周报可见人员详细信息
     */
    @Override
    @GetMapping("/{id}")
    public Result<WeekVisibleUser> detail(@PathVariable("id") String id) {
        return Result.success(weekVisibleUserService.detailById(id));
    }

    /**
     * 增加周报可见人员信息
     *
     * @param weekVisibleUser 周报可见人员增加实体
     * @return 增加后的周报可见人员信息
     */
    @Override
    @PostMapping
    public Result<WeekVisibleUser> add(@RequestBody @Validated(AddGroup.class) WeekVisibleUser weekVisibleUser) {
        weekVisibleUserService.saveWeekVisibleUser(weekVisibleUser);
        return Result.success(weekVisibleUser);
    }

    /**
     * 修改周报可见人员信息
     *
     * @param id              周报可见人员ID
     * @param weekVisibleUser 周报可见人员修改实体
     * @return 修改后的周报可见人员信息
     */
    @Override
    @PutMapping("/{id}")
    public Result<WeekVisibleUser> updateById(@PathVariable("id") String id, @RequestBody @Validated(UpdateGroup.class) WeekVisibleUser weekVisibleUser) {
        weekVisibleUser.setId(id);
        weekVisibleUserService.updateWeekVisibleUserById(weekVisibleUser);
        return Result.success(weekVisibleUserService.getById(id));
    }


    /**
     * 删除周报可见人员信息
     *
     * @param id 周报可见人员ID
     * @return 删除结果
     */
    @Override
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") String id) {
        weekVisibleUserService.deleteById(id);
        return Result.success(true);
    }

    /**
     * 删除周报可见人员信息
     *
     * @param idList 周报可见人员ID数组
     * @return 删除结果
     */
    @Override
    @DeleteMapping("/batch-remove")
    public Result<Boolean> batchRemove(@RequestBody List<String> idList) {
        weekVisibleUserService.removeByIds(idList);
        return Result.success(true);
    }
}
