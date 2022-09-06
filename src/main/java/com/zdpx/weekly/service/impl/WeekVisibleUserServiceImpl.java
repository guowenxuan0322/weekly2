package com.zdpx.weekly.service.impl;

import com.zdpx.weekly.pojo.entity.WeekVisibleUser;
import com.zdpx.weekly.mapper.WeekVisibleUserMapper;
import com.zdpx.weekly.service.WeekVisibleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 周报可见人员信息 服务实现类
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Service
public class WeekVisibleUserServiceImpl extends ServiceImpl<WeekVisibleUserMapper, WeekVisibleUser> implements WeekVisibleUserService {

    /**
     * 获取周报可见人员详情
     *
     * @param id ID
     * @return 单个周报可见人员详情
     */
    @Override
    public WeekVisibleUser detailById(String id) {
        return this.getById(id);
    }

    /**
     * 增加周报可见人员信息
     *
     * @param weekVisibleUser 周报可见人员对象
     */
    @Override
    public void saveWeekVisibleUser(WeekVisibleUser weekVisibleUser) {
        this.baseMapper.insert(weekVisibleUser);
    }

    /**
     * 修改周报可见人员信息
     *
     * @param weekVisibleUser 周报可见人员对象
     */
    @Override
    public void updateWeekVisibleUserById(WeekVisibleUser weekVisibleUser) {
        this.baseMapper.updateById(weekVisibleUser);
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
