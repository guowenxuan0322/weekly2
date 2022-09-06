package com.zdpx.weekly.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zdpx.weekly.pojo.entity.Week;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zdpx.weekly.pojo.query.WeekQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 周报信息 Mapper 接口
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Mapper
public interface WeekMapper extends BaseMapper<Week> {
    /**
     * 可见周报-分页查询
     * @param page
     * @param weekQuery
     * @return
     */
    IPage<Week> selectVisibleWeek(IPage<Week> page,  @Param("weekQuery") WeekQuery weekQuery, @Param("userId") String userId, @Param("childDeptIds") List<String> childDeptIds);
}
