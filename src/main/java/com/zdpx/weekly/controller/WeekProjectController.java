package com.zdpx.weekly.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zdpx.pxframework.core.validator.group.AddGroup;
import com.zdpx.pxframework.core.validator.group.UpdateGroup;
import com.zdpx.pxframework.core.vo.PageResult;
import com.zdpx.pxframework.core.vo.Result;
import com.zdpx.weekly.api.WeekProjectControllerApi;
import com.zdpx.weekly.pojo.entity.WeekProject;
import com.zdpx.weekly.pojo.vo.WeekProjectVo;
import com.zdpx.weekly.service.WeekProjectService;
import com.zdpx.weekly.pojo.query.WeekProjectQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 周报项目 接口控制器
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@RestController
@RequestMapping("/week-project")
public class WeekProjectController implements WeekProjectControllerApi {
    @Resource
    private WeekProjectService weekProjectService;


    /**
     * 获取条件查询信息
     *
     * @param weekProjectQuery 查询条件
     * @return 查询封装
     */
    private LambdaQueryWrapper<WeekProject> getWeekProjectQueryWrapper(WeekProjectQuery weekProjectQuery) {
        LambdaQueryWrapper<WeekProject> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(weekProjectQuery.getWeekProjectId())) {
            wrapper.like(WeekProject::getWeekProjectId, weekProjectQuery.getWeekProjectId());
        }
        if (!StringUtils.isEmpty(weekProjectQuery.getWeekId())) {
            wrapper.like(WeekProject::getWeekId, weekProjectQuery.getWeekId());
        }
        if (!StringUtils.isEmpty(weekProjectQuery.getProjectId())) {
            wrapper.like(WeekProject::getProjectId, weekProjectQuery.getProjectId());
        }
        if (!StringUtils.isEmpty(weekProjectQuery.getProjectName())) {
            wrapper.like(WeekProject::getProjectName, weekProjectQuery.getProjectName());
        }
        if (!StringUtils.isEmpty(weekProjectQuery.getProjectContent())) {
            wrapper.like(WeekProject::getProjectContent, weekProjectQuery.getProjectContent());
        }
        return wrapper;
    }

    /**
     * 按照条件查询周报项目信息
     *
     * @param weekProjectQuery 查询条件
     * @return 条件结果信息
     */
    @Override
    @GetMapping
    public Result<List<WeekProject>> list(WeekProjectQuery weekProjectQuery) {
        return Result.success(weekProjectService.list(getWeekProjectQueryWrapper(weekProjectQuery)));
    }

    /**
     * 分页及条件查询周报项目信息
     *
     * @param size             分页大小
     * @param page             页码
     * @param weekProjectQuery 查询条件
     * @return 分页结果信息
     */
    @Override
    @GetMapping("/{size}/{page}")
    public Result<PageResult<WeekProject>> search(@PathVariable("size") Long size, @PathVariable("page") Long page, WeekProjectQuery weekProjectQuery) {
        if (page <= 0) {
            page = 1L;
        }
        if (size <= 0) {
            size = 10L;
        }
        Page<WeekProject> pageParam = new Page<>(page, size);

        weekProjectService.page(pageParam, getWeekProjectQueryWrapper(weekProjectQuery));
        PageResult<WeekProject> pageResult = new PageResult<WeekProject>(pageParam.getTotal(), pageParam.getSize(), pageParam.getCurrent(), pageParam.getRecords());

        return Result.success(pageResult);
    }

    /**
     * 分页及条件查询周报项目信息
     *
     * @param size             分页大小
     * @param page             页码
     * @param weekProjectQuery 查询条件
     * @return 分页结果信息
     */
    @Override
    @GetMapping("search-project/{size}/{page}")
    public Result<PageResult<WeekProjectVo>> searchByProject(@PathVariable("size") Long size, @PathVariable("page") Long page, WeekProjectQuery weekProjectQuery) {
        if (page <= 0) {
            page = 1L;
        }
        if (size <= 0) {
            size = 10L;
        }
        Page<WeekProject> pageParam = new Page<>(page, size);
        PageResult<WeekProjectVo> pageResult = weekProjectService.queryWeekProjectPage(pageParam, weekProjectQuery);
        return Result.success(pageResult);
    }

    /**
     * 根据ID获取周报项目详情
     *
     * @param id 周报项目ID
     * @return 周报项目详细信息
     */
    @Override
    @GetMapping("/{id}")
    public Result<WeekProject> detail(@PathVariable("id") String id) {
        return Result.success(weekProjectService.detailById(id));
    }

    /**
     * 增加周报项目信息
     *
     * @param weekProject 周报项目增加实体
     * @return 增加后的周报项目信息
     */
    @Override
    @PostMapping
    public Result<WeekProject> add(@RequestBody @Validated(AddGroup.class) WeekProject weekProject) {
        weekProjectService.saveWeekProject(weekProject);
        return Result.success(weekProject);
    }

    /**
     * 修改周报项目信息
     *
     * @param id          周报项目ID
     * @param weekProject 周报项目修改实体
     * @return 修改后的周报项目信息
     */
    @Override
    @PutMapping("/{id}")
    public Result<WeekProject> updateById(@PathVariable("id") String id, @RequestBody @Validated(UpdateGroup.class) WeekProject weekProject) {
        weekProject.setProjectId(id);
        weekProjectService.updateWeekProjectById(weekProject);
        return Result.success(weekProjectService.getById(id));
    }


    /**
     * 删除周报项目信息
     *
     * @param id 周报项目ID
     * @return 删除结果
     */
    @Override
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") String id) {
        weekProjectService.deleteById(id);
        return Result.success(true);
    }

    /**
     * 删除周报项目信息
     *
     * @param idList 周报项目ID数组
     * @return 删除结果
     */
    @Override
    @DeleteMapping("/batch-remove")
    public Result<Boolean> batchRemove(@RequestBody List<String> idList) {
        weekProjectService.removeByIds(idList);
        return Result.success(true);
    }
}
