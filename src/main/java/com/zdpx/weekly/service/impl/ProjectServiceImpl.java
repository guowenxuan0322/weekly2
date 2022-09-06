package com.zdpx.weekly.service.impl;

import com.zdpx.weekly.pojo.entity.Project;
import com.zdpx.weekly.mapper.ProjectMapper;
import com.zdpx.weekly.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * 项目信息 服务实现类
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {
    /**
     * 获取项目详情
     *
     * @param id ID
     * @return 单个项目详情
     */
    @Override
    public Project detailById(String id) {
        return this.getById(id);
    }

    /**
     * 增加项目信息
     *
     * @param project 项目对象
     */
    @Override
    public void saveProject(Project project) {
        this.baseMapper.insert(project);
    }

    /**
     * 修改项目信息
     *
     * @param project 项目对象
     */
    @Override
    public void updateProjectById(Project project) {
        this.baseMapper.updateById(project);
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
