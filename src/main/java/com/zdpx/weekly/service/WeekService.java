package com.zdpx.weekly.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zdpx.pxframework.core.vo.PageResult;
import com.zdpx.weekly.pojo.entity.Week;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zdpx.weekly.pojo.query.WeekQuery;
import com.zdpx.weekly.pojo.vo.WeekVo;


/**
 * 周报 服务类
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
public interface WeekService extends IService<Week> {
    /**
     * 获取周报详情
     *
     * @param id ID
     * @return 单个周报详情
     */
    Week detailById(String id);

    /**
     * 增加周报信息
     *
     * @param weekVo 周报信息
     */
    void saveWeek(WeekVo weekVo);

    /**
     * 修改周报信息
     *
     * @param weekVo 周报对象
     */
    void updateWeekById(WeekVo weekVo);

    /**
     * 根据ID删除信息
     *
     * @param id ID
     */
    void deleteById(String id);

    /**
     * 可见周报-分页查询
     * @param page
     * @param weekQuery
     * @return
     */
    PageResult<WeekVo> queryVisiblePage(Page page, WeekQuery weekQuery);

}
