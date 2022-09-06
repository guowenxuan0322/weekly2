package com.zdpx.weekly.service;

import com.zdpx.weekly.pojo.entity.WeekComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 周报评论 服务类
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
public interface WeekCommentService extends IService<WeekComment> {
    /**
     * 获取周报评论详情
     *
     * @param id ID
     * @return 单个周报评论详情
     */
    WeekComment detailById(String id);

    /**
     * 增加周报评论信息
     *
     * @param weekComment 周报评论信息
     */
    void saveWeekComment(WeekComment weekComment);

    /**
     * 修改周报评论信息
     *
     * @param weekComment 周报评论对象
     */
    void updateWeekCommentById(WeekComment weekComment);

    /**
     * 根据ID删除信息
     *
     * @param id ID
     */
    void deleteById(String id);

    /**
     * 获取周报评论详情
     * @param weekIds weekIds
     *
     */
    List<WeekComment> listByWeekIds(List<String> weekIds);
}
