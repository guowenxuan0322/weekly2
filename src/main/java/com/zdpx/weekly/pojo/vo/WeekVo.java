package com.zdpx.weekly.pojo.vo;

import com.zdpx.weekly.pojo.entity.Week;
import com.zdpx.weekly.pojo.entity.WeekComment;
import com.zdpx.weekly.pojo.entity.WeekProject;
import lombok.Data;

import java.util.List;

/**
 * 周报VO表
 *
 * @Description:
 * @Author: guowx
 * @Date: 2022-06-03 22:44:38
 */
@Data
public class WeekVo extends Week {
    private List<WeekProject> weekProjectList;
    private String visibleUserIds;
    private List<WeekComment> weekCommentList;
}
