package com.yhf.xuedaoqian.api;

import com.yhf.xuedaoqian.model.Leave;
import com.yhf.xuedaoqian.model.reps.LeaveInfoReps;
import com.yhf.xuedaoqian.model.reps.LeaveReps;

import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/11 15:01
 */
public interface LeaveApi {

    /**
    * @Description 请假
    * @Param [leave]
    * @return void
    * @Author yaohengfeng
    * @Date 2020/4/11
    */
    void insertLeave(Leave leave);

    /***
    * @Description 修改请假信息
    * @Param [leave]
    * @return void
    * @Author yaohengfeng
    * @Date 2020/4/11
    */
    void updateLeave(Leave leave);

    /**
    * @Description 查询请假详情
    * @Param [leaveId]
    * @return com.yhf.xuedaoqian.model.Leave
    * @Author yaohengfeng
    * @Date 2020/4/11
    */
    LeaveInfoReps selectLeaveByLeaveId(String leaveId);

    /**
    * @Description 查询本人请假列表
    * @Param [applicantId]
    * @return java.util.List<com.yhf.xuedaoqian.model.Leave>
    * @Author yaohengfeng
    * @Date 2020/4/11
    */
    List<LeaveReps> selectLeaveByUserId(String studentId);

    /**
     * @Description 查询班级请假列表
     * @Param [applicantId]
     * @return java.util.List<com.yhf.xuedaoqian.model.Leave>
     * @Author yaohengfeng
     * @Date 2020/4/11
     */
    List<Leave> selectLeaveByClassId(String classId);
}
