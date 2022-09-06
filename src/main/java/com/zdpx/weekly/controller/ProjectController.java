package com.zdpx.weekly.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zdpx.pxframework.core.validator.group.AddGroup;
import com.zdpx.pxframework.core.validator.group.UpdateGroup;
import com.zdpx.pxframework.core.vo.PageResult;
import com.zdpx.pxframework.core.vo.Result;
import com.zdpx.weekly.api.ProjectControllerApi;
import com.zdpx.weekly.pojo.entity.Project;
import com.zdpx.weekly.service.ProjectService;
import com.zdpx.weekly.pojo.query.ProjectQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 项目 接口控制器
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@RestController
@RequestMapping("/project")
public class ProjectController implements ProjectControllerApi {
    @Resource
    private ProjectService projectService;


    /**
     * 获取条件查询信息
     *
     * @param projectQuery 查询条件
     * @return 查询封装
     */
    private LambdaQueryWrapper<Project> getProjectQueryWrapper(ProjectQuery projectQuery) {
        LambdaQueryWrapper<Project> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(projectQuery.getProjectId())) {
            wrapper.like(Project::getProjectId, projectQuery.getProjectId());
        }
        if (!StringUtils.isEmpty(projectQuery.getSerialNum())) {
            wrapper.like(Project::getSerialNum, projectQuery.getSerialNum());
        }
        if (!StringUtils.isEmpty(projectQuery.getProjectCode())) {
            wrapper.like(Project::getProjectCode, projectQuery.getProjectCode());
        }
        if (!StringUtils.isEmpty(projectQuery.getProjectName())) {
            wrapper.like(Project::getProjectName, projectQuery.getProjectName());
        }
        if (!StringUtils.isEmpty(projectQuery.getDescribe())) {
            wrapper.like(Project::getDescribe, projectQuery.getDescribe());
        }
        return wrapper;
    }

    /**
     * 按照条件查询项目信息
     *
     * @param projectQuery 查询条件
     * @return 条件结果信息
     */
    @Override
    @GetMapping
    public Result<List<Project>> list(ProjectQuery projectQuery) {
        return Result.success(projectService.list(getProjectQueryWrapper(projectQuery)));
    }

    /**
     * 分页及条件查询项目信息
     *
     * @param page         页码
     * @param size         分页大小
     * @param projectQuery 查询条件
     * @return 分页结果信息
     */
    @Override
    @GetMapping("/{size}/{page}")
    public Result<PageResult<Project>> search(@PathVariable("size") Long size, @PathVariable("page") Long page, ProjectQuery projectQuery) {
        if (page <= 0) {
            page = 1L;
        }
        if (size <= 0) {
            size = 10L;
        }
        Page<Project> pageParam = new Page<>(page, size);

        projectService.page(pageParam, getProjectQueryWrapper(projectQuery));
        PageResult<Project> pageResult = new PageResult<Project>(pageParam.getTotal(), pageParam.getSize(), pageParam.getCurrent(), pageParam.getRecords());

        return Result.success(pageResult);
    }

    /**
     * 根据ID获取项目详情
     *
     * @param id 项目ID
     * @return 项目详细信息
     */
    @Override
    @GetMapping("/{id}")
    public Result<Project> detail(@PathVariable("id") String id) {
        return Result.success(projectService.detailById(id));
    }

    /**
     * 增加项目信息
     *
     * @param project 项目增加实体
     * @return 增加后的项目信息
     */
    @Override
    @PostMapping
    public Result<Project> add(@RequestBody @Validated(AddGroup.class) Project project) {
        projectService.saveProject(project);
        return Result.success(project);
    }

    /**
     * 修改项目信息
     *
     * @param id      项目ID
     * @param project 项目修改实体
     * @return 修改后的项目信息
     */
    @Override
    @PutMapping("/{id}")
    public Result<Project> updateById(@PathVariable("id") String id, @RequestBody @Validated(UpdateGroup.class) Project project) {
        project.setProjectId(id);
        projectService.updateProjectById(project);
        return Result.success(projectService.getById(id));
    }


    /**
     * 删除项目信息
     *
     * @param id 项目ID
     * @return 删除结果
     */
    @Override
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") String id) {
        projectService.deleteById(id);
        return Result.success(true);
    }

    /**
     * 删除项目信息
     *
     * @param idList 项目ID数组
     * @return 删除结果
     */
    @Override
    @DeleteMapping("/batch-remove")
    public Result<Boolean> batchRemove(@RequestBody List<String> idList) {
        projectService.removeByIds(idList);
        return Result.success(true);
    }
}
