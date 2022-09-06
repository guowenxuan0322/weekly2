package com.zdpx.weekly.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zdpx.pxframework.core.vo.PageResult;
import com.zdpx.weekly.pojo.entity.WeekProject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zdpx.weekly.pojo.query.WeekProjectQuery;
import com.zdpx.weekly.pojo.vo.WeekProjectVo;

import java.util.List;


/**
 * 周报项目 服务类
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
public interface WeekProjectService extends IService<WeekProject> {
    /**
     * 获取周报项目详情
     *
     * @param id ID
     * @return 单个周报项目详情
     */
    WeekProject detailById(String id);

    /**
     * 增加周报项目信息
     *
     * @param weekProject 周报项目信息
     */
    void saveWeekProject(WeekProject weekProject);

    /**
     * 修改周报项目信息
     *
     * @param weekProject 周报项目对象
     */
    void updateWeekProjectById(WeekProject weekProject);

    /**
     * 根据ID删除信息
     *
     * @param id ID
     */
    void deleteById(String id);

    /**
     * 根据weedkIds 查询
     * @param weekIds
     * @return
     */
    List<WeekProject> listByWeekIds(List<String> weekIds);


    /**
     * 根据项目-分页查询
     * @param page
     * @param weekProjectQuery
     * @return
     */
    PageResult<WeekProjectVo> queryWeekProjectPage(Page page, WeekProjectQuery weekProjectQuery);
}
