package com.zdpx.weekly.service;

import com.zdpx.weekly.pojo.entity.WeekVisibleUser;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 周报可见人员 服务类
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
public interface WeekVisibleUserService extends IService<WeekVisibleUser> {
    /**
     * 获取周报可见人员详情
     *
     * @param id ID
     * @return 单个周报可见人员详情
     */
    WeekVisibleUser detailById(String id);

    /**
     * 增加周报可见人员信息
     *
     * @param weekVisibleUser 周报可见人员信息
     */
    void saveWeekVisibleUser(WeekVisibleUser weekVisibleUser);

    /**
     * 修改周报可见人员信息
     *
     * @param weekVisibleUser 周报可见人员对象
     */
    void updateWeekVisibleUserById(WeekVisibleUser weekVisibleUser);

    /**
     * 根据ID删除信息
     *
     * @param id ID
     */
    void deleteById(String id);
}
