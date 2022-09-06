package com.zdpx.weekly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zdpx.weekly.pojo.entity.Week;
import com.zdpx.weekly.pojo.entity.WeekComment;
import com.zdpx.weekly.mapper.WeekCommentMapper;
import com.zdpx.weekly.service.WeekCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 周报评论信息 服务实现类
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Service
public class WeekCommentServiceImpl extends ServiceImpl<WeekCommentMapper, WeekComment> implements WeekCommentService {
    /**
     * 获取周报评论详情
     *
     * @param id ID
     * @return 单个周报评论详情
     */
    @Override
    public WeekComment detailById(String id) {
        return this.getById(id);
    }

    /**
     * 增加周报评论信息
     *
     * @param weekComment 周报评论对象
     */
    @Override
    public void saveWeekComment(WeekComment weekComment) {
        this.baseMapper.insert(weekComment);
    }

    /**
     * 修改周报评论信息
     *
     * @param weekComment 周报评论对象
     */
    @Override
    public void updateWeekCommentById(WeekComment weekComment) {
        this.baseMapper.updateById(weekComment);
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
    public List<WeekComment> listByWeekIds(List<String> weekIds) {
        List<WeekComment> list = this.list(new LambdaQueryWrapper<WeekComment>()
                .in(WeekComment::getWeekId, weekIds)
                .orderByDesc(WeekComment::getCreateTime)
        );
        return list;
    }
}
