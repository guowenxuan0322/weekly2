package com.zdpx.weekly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zdpx.pxframework.core.vo.PageResult;
import com.zdpx.weekly.pojo.entity.Week;
import com.zdpx.weekly.mapper.WeekMapper;
import com.zdpx.weekly.pojo.entity.WeekProject;
import com.zdpx.weekly.pojo.entity.WeekVisibleUser;
import com.zdpx.weekly.pojo.query.WeekQuery;
import com.zdpx.weekly.pojo.vo.WeekVo;
import com.zdpx.weekly.service.WeekProjectService;
import com.zdpx.weekly.service.WeekService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zdpx.weekly.service.WeekVisibleUserService;
import com.zdpx.weekly.util.SnowflakeIdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 周报信息 服务实现类
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Service
public class WeekServiceImpl extends ServiceImpl<WeekMapper, Week> implements WeekService {
    @Resource
    private WeekProjectService weekProjectService;
    @Resource
    private WeekVisibleUserService weekVisibleUserService;
    @Resource
    private WeekMapper weekMapper;

    /**
     * 获取周报详情
     *
     * @param id ID
     * @return 单个周报详情
     */
    @Override
    public Week detailById(String id) {
        return this.getById(id);
    }

    /**
     * 增加周报信息
     *
     * @param weekVo 周报对象
     */
    @Override
    @Transactional
    public void saveWeek(WeekVo weekVo) {
        SnowflakeIdWorker sf = new SnowflakeIdWorker(2,2,2);
        String weekId = String.valueOf(sf.nextId());
        weekVo.setWeekId(weekId);
        this.baseMapper.insert(weekVo);
        //保存项目周报
        if (!CollectionUtils.isEmpty(weekVo.getWeekProjectList())) {
            List<WeekProject> weekProjectList = weekVo.getWeekProjectList();
            weekProjectList.stream().forEach(e -> e.setWeekId(weekId));
            weekProjectService.saveBatch(weekProjectList);
        }
        //保存周报可见人员表
        if (StringUtils.isNotBlank(weekVo.getVisibleUserIds())) {
            ArrayList<WeekVisibleUser> weekVisibleUsers = new ArrayList<>();
            String[] split = weekVo.getVisibleUserIds().split(",");
            for (String userId : split) {
                WeekVisibleUser weekVisibleUser = new WeekVisibleUser();
                weekVisibleUser.setVisibleUserId(userId);
                weekVisibleUser.setWeekId(weekId);
                weekVisibleUsers.add(weekVisibleUser);
            }
            weekVisibleUserService.saveBatch(weekVisibleUsers);
        }
        //保存周报可见人员表
        if (StringUtils.isNotBlank(weekVo.getVisibleUserIds())) {
            List<WeekVisibleUser> weekVisibleUserList = weekVisibleUserService.list(new LambdaQueryWrapper<WeekVisibleUser>().eq(WeekVisibleUser::getWeekId, weekVo.getWeekId()));
            weekVisibleUserService.removeByIds(weekVisibleUserList);
            ArrayList<WeekVisibleUser> weekVisibleUsers = new ArrayList<>();
            String[] split = weekVo.getVisibleUserIds().split(",");
            for (String userId : split) {
                WeekVisibleUser weekVisibleUser = new WeekVisibleUser();
                weekVisibleUser.setVisibleUserId(userId);
                weekVisibleUser.setWeekId(weekId);
                weekVisibleUsers.add(weekVisibleUser);
            }
            weekVisibleUserService.saveBatch(weekVisibleUsers);
        }
    }

    /**
     * 修改周报信息
     *
     * @param weekVo 周报对象
     */
    @Override
    public void updateWeekById(WeekVo weekVo) {
        this.baseMapper.updateById(weekVo);
        //保存项目周报
        if (!CollectionUtils.isEmpty(weekVo.getWeekProjectList())) {
            List<WeekProject> weekProjectList = weekVo.getWeekProjectList();
            weekProjectService.saveOrUpdateBatch(weekProjectList);
        }
        //
    }

    /**
     * 根据ID删除
     *
     * @param id ID
     */
    @Override
    public void deleteById(String id) {
        this.removeById(id);
    }

    @Override
    public PageResult<WeekVo> queryVisiblePage(Page pageParam, WeekQuery weekQuery) {
        //TODO by guowx 获取当前登录人的所有子部门Id; 当前登录人user_id

        List<String> childDeptIds = Arrays.asList("1009");
        IPage iPage = weekMapper.selectVisibleWeek(pageParam, weekQuery, "2002", childDeptIds);
        List<Week> weeks = iPage.getRecords();
        List<String> weekIds = weeks.stream().map(Week::getWeekId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(weekIds)) {
            PageResult<WeekVo> pageResult = new PageResult<>(iPage.getTotal(), iPage.getSize(), iPage.getCurrent(), null);
            return pageResult;
        }
        List<WeekProject> weekProjects = weekProjectService.listByWeekIds(weekIds);
        Map<String, List<WeekProject>> map = weekProjects.stream().collect(Collectors.groupingBy(WeekProject::getWeekId));
        List<WeekVo> weekVos = new ArrayList<>();
        for (Week week : weeks) {
            WeekVo weekVo = new WeekVo();
            BeanUtils.copyProperties(week,weekVo);
            weekVo.setWeekProjectList(map.get(week.getWeekId()));
            weekVos.add(weekVo);
        }
        PageResult<WeekVo> pageResult = new PageResult<>(iPage.getTotal(), iPage.getSize(), iPage.getCurrent(), weekVos);
        return pageResult;
    }
}
