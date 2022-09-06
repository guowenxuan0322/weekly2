package com.zdpx.weekly.service;

import com.zdpx.weekly.pojo.entity.Project;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 项目 服务类
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
public interface ProjectService extends IService<Project> {
    /**
     * 获取项目详情
     *
     * @param id ID
     * @return 单个项目详情
     */
    Project detailById(String id);

    /**
     * 增加项目信息
     *
     * @param project 项目信息
     */
    void saveProject(Project project);

    /**
     * 修改项目信息
     *
     * @param project 项目对象
     */
    void updateProjectById(Project project);

    /**
     * 根据ID删除信息
     *
     * @param id ID
     */
    void deleteById(String id);
}
