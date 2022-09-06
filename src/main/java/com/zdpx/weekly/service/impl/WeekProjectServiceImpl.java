package com.zdpx.weekly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zdpx.pxframework.core.vo.PageResult;
import com.zdpx.weekly.pojo.entity.WeekProject;
import com.zdpx.weekly.mapper.WeekProjectMapper;
import com.zdpx.weekly.pojo.query.WeekProjectQuery;
import com.zdpx.weekly.pojo.vo.WeekProjectVo;
import com.zdpx.weekly.pojo.vo.WeekVo;
import com.zdpx.weekly.service.WeekProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * 周报项目信息 服务实现类
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Service
public class WeekProjectServiceImpl extends ServiceImpl<WeekProjectMapper, WeekProject> implements WeekProjectService {
    @Resource
    WeekProjectMapper weekProjectMapper;
    /**
     * 获取周报项目详情
     *
     * @param id ID
     * @return 单个周报项目详情
     */
    @Override
    public WeekProject detailById(String id) {
        return this.getById(id);
    }

    /**
     * 增加周报项目信息
     *
     * @param weekProject 周报项目对象
     */
    @Override
    public void saveWeekProject(WeekProject weekProject) {
        this.baseMapper.insert(weekProject);
    }

    /**
     * 修改周报项目信息
     *
     * @param weekProject 周报项目对象
     */
    @Override
    public void updateWeekProjectById(WeekProject weekProject) {
        this.baseMapper.updateById(weekProject);
    }

    /**
     * 根据weedkIds 查询
     * @param weekIds
     * @return
     */
    @Override
    public List<WeekProject> listByWeekIds(List<String> weekIds) {
        List<WeekProject> list = this.list(new LambdaQueryWrapper<WeekProject>()
                .in(WeekProject::getWeekId, weekIds)
        );
        return list;
    }

    @Override
    public PageResult<WeekProjectVo> queryWeekProjectPage(Page page, WeekProjectQuery weekProjectQuery) {
        //TODO by guowx 获取当前登录人的所有子部门Id;
        List<String> childDeptIds = Arrays.asList("1001");
        IPage iPage = weekProjectMapper.selectWeekProjectPage(page, weekProjectQuery, childDeptIds);
        PageResult<WeekProjectVo> pageResult = new PageResult<>(iPage.getTotal(), iPage.getSize(), iPage.getCurrent(), iPage.getRecords());
        return pageResult;
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
}
