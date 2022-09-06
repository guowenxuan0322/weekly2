package com.zdpx.weekly.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zdpx.pxframework.core.validator.group.AddGroup;
import com.zdpx.pxframework.core.validator.group.UpdateGroup;
import com.zdpx.pxframework.core.vo.PageResult;
import com.zdpx.pxframework.core.vo.Result;
import com.zdpx.weekly.api.WeekControllerApi;
import com.zdpx.weekly.pojo.entity.Week;
import com.zdpx.weekly.pojo.entity.WeekComment;
import com.zdpx.weekly.pojo.entity.WeekProject;
import com.zdpx.weekly.pojo.query.WeekQuery;
import com.zdpx.weekly.pojo.vo.WeekVo;
import com.zdpx.weekly.service.WeekCommentService;
import com.zdpx.weekly.service.WeekProjectService;
import com.zdpx.weekly.service.WeekService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 周报 接口控制器
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@RestController
@RequestMapping("/week")
public class WeekController implements WeekControllerApi {
    @Resource
    private WeekService weekService;
    @Resource
    private WeekProjectService weekProjectService;
    @Resource
    private WeekCommentService weekCommentService;

    /**
     * 获取条件查询信息
     *
     * @param weekQuery 查询条件
     * @return 查询封装
     */
    private Wrapper<Week> getWeekQueryWrapper(WeekQuery weekQuery) {
        LambdaQueryWrapper<Week> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Week::getCreateTime);
        if (!StringUtils.isEmpty(weekQuery.getWeekId())) {
            wrapper.like(Week::getWeekId, weekQuery.getWeekId());
        }
        if (!StringUtils.isEmpty(weekQuery.getUserId())) {
            wrapper.like(Week::getUserId, weekQuery.getUserId());
        }
        if (!StringUtils.isEmpty(weekQuery.getUserName())) {
            wrapper.like(Week::getUserName, weekQuery.getUserName());
        }
        if (!StringUtils.isEmpty(weekQuery.getUserDept())) {
            wrapper.like(Week::getUserDept, weekQuery.getUserDept());
        }
        if (!StringUtils.isEmpty(weekQuery.getContent())) {
            wrapper.like(Week::getContent, weekQuery.getContent());
        }
        if (weekQuery.getVisibleType() != null) {
            wrapper.eq(Week::getVisibleType, weekQuery.getVisibleType());
        }
        if (weekQuery.getStartTime() != null && weekQuery.getEndTime() != null ) {
            wrapper.le(Week::getCreateTime, weekQuery.getEndTime());
            wrapper.ge(Week::getCreateTime, weekQuery.getStartTime());
        }
        /*
        //查询本周周报
        if(SelectTypeEnum.CURRENTWEEK.equals(weekQuery.getSelectType())){
            wrapper.le(Week::getCreateTime, DateUtil.getBeginDayOfWeek());
            wrapper.ge(Week::getCreateTime, DateUtil.getEndDayOfLastWeek());
        }*/
        return wrapper;
    }

    /**
     * 按照条件查询周报信息
     *
     * @param weekQuery 查询条件
     * @return 条件结果信息
     */
    @Override
    @GetMapping
    public Result<List<Week>> list(WeekQuery weekQuery) {
        return Result.success(weekService.list(getWeekQueryWrapper(weekQuery)));
    }

    /**
     * 本人-分页及条件查询周报信息
     *
     * @param size      分页大小
     * @param page      页码
     * @param weekQuery 查询条件
     * @return 分页结果信息
     */
    @Override
    @GetMapping("/search-self/{size}/{page}")
    public Result<PageResult<WeekVo>> searchSelf(@PathVariable("size") Long size, @PathVariable("page") Long page, WeekQuery weekQuery) {
        if (page <= 0) {
            page = 1L;
        }
        if (size <= 0) {
            size = 10L;
        }
        Page<Week> pageParam = new Page<>(page, size);
        weekService.page(pageParam, getWeekQueryWrapper(weekQuery));
        List<Week> weeks = pageParam.getRecords();
        List<String> weekIds = weeks.stream().map(Week::getWeekId).collect(Collectors.toList());
        //查询项目周报
        List<WeekProject> weekProjects = weekProjectService.listByWeekIds(weekIds);
        Map<String, List<WeekProject>> weekProjectMap = weekProjects.stream().collect(Collectors.groupingBy(WeekProject::getWeekId));
        //查询周报评论
        List<WeekComment> weekComments = weekCommentService.listByWeekIds(weekIds);
        Map<String, List<WeekComment>> weekCommentMap = weekComments.stream().collect(Collectors.groupingBy(WeekComment::getWeekId));
        //组装结果
        List<WeekVo> weekVos = new ArrayList<>();
        for (Week week : weeks) {
            WeekVo weekVo = new WeekVo();
            BeanUtils.copyProperties(week,weekVo);
            weekVo.setWeekProjectList(weekProjectMap.get(week.getWeekId()));
            weekVo.setWeekCommentList(weekCommentMap.get(week.getWeekId()));
            weekVos.add(weekVo);
        }
        PageResult<WeekVo> pageResult = new PageResult<WeekVo>(pageParam.getTotal(), pageParam.getSize(), pageParam.getCurrent(), weekVos);
        return Result.success(pageResult);
    }

    /**
     * 可见周报-分页及条件查询周报信息
     *
     * @param size      分页大小
     * @param page      页码
     * @param weekQuery 查询条件
     * @return 分页结果信息
     */
    @Override
    @GetMapping("/search-visible/{size}/{page}")
    public Result<PageResult<WeekVo>> searchVisible(@PathVariable("size") Long size, @PathVariable("page") Long page, WeekQuery weekQuery) {
        if (page <= 0) {
            page = 1L;
        }
        if (size <= 0) {
            size = 10L;
        }
        Page<Week> pageParam = new Page<>(page, size);
        PageResult<WeekVo> pageResult = weekService.queryVisiblePage(pageParam,weekQuery);
        return Result.success(pageResult);
    }

    /**
     * 根据ID获取周报详情
     *
     * @param id 周报ID
     * @return 周报详细信息
     */
    @Override
    @GetMapping("/{id}")
    public Result<Week> detail(@PathVariable("id") String id) {
        return Result.success(weekService.detailById(id));
    }

    /**
     * 增加周报信息
     *
     * @param weekVo 周报增加实体
     * @return 增加后的周报信息
     */
    @Override
    @PostMapping
    public Result<WeekVo> add(@RequestBody @Validated(AddGroup.class) WeekVo weekVo) {
        weekService.saveWeek(weekVo);
        return Result.success(weekVo);
    }

    /**
     * 修改周报信息
     *
     * @param id   周报ID
     * @param weekVo 周报修改实体
     * @return 修改后的周报信息
     */
    @Override
    @PutMapping("/{id}")
    public Result<Week> updateById(@PathVariable("id") String id, @RequestBody @Validated(UpdateGroup.class) WeekVo weekVo) {
        weekVo.setWeekId(id);
        weekService.updateWeekById(weekVo);
        return Result.success(weekService.getById(id));
    }


    /**
     * 删除周报信息
     *
     * @param id 周报ID
     * @return 删除结果
     */
    @Override
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") String id) {
        weekService.deleteById(id);
        return Result.success(true);
    }

    /**
     * 删除周报信息
     *
     * @param idList 周报ID数组
     * @return 删除结果
     */
    @Override
    @DeleteMapping("/batch-remove")
    public Result<Boolean> batchRemove(@RequestBody List<String> idList) {
        weekService.removeByIds(idList);
        return Result.success(true);
    }
}
