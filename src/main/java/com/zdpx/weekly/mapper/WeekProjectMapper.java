package com.zdpx.weekly.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zdpx.weekly.pojo.entity.Week;
import com.zdpx.weekly.pojo.entity.WeekProject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zdpx.weekly.pojo.query.WeekProjectQuery;
import com.zdpx.weekly.pojo.query.WeekQuery;
import com.zdpx.weekly.pojo.vo.WeekProjectVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 周报项目信息 Mapper 接口
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Mapper
public interface WeekProjectMapper extends BaseMapper<WeekProject> {

    /**
     * 根据项目-分页查询项目周报
     * @param page
     * @param weekProjectQuery
     * @param childDeptIds
     * @return
     */
    IPage<Week> selectWeekProjectPage(IPage<WeekProjectVo> page, @Param("weekProjectQuery") WeekProjectQuery weekProjectQuery, @Param("childDeptIds") List<String> childDeptIds);
}
