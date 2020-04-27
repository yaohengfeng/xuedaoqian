package com.yhf.xuedaoqian.dao;

import com.yhf.xuedaoqian.model.Leave;
import com.yhf.xuedaoqian.model.reps.LeaveReps;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/25 11:16
 */
@Mapper
public interface LeaveDao {

    void insertLeave(Leave leave);

    void updateLeave(Leave leave);

    Leave selectLeaveByLeaveId(String leaveId);

    List<Leave> selectLeaveByUserId(@Param("studentId") String studentId,@Param("classId") String classId);

    List<LeaveReps> selectLeaveByUserId1(String studentId);

    List<Leave> selectLeaveByClassId(String classId);
}
