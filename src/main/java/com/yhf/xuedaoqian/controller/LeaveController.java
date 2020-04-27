package com.yhf.xuedaoqian.controller;

import com.yhf.xuedaoqian.api.LeaveApi;
import com.yhf.xuedaoqian.model.Leave;
import com.yhf.xuedaoqian.model.SchoolClass;
import com.yhf.xuedaoqian.model.reps.LeaveInfoReps;
import com.yhf.xuedaoqian.model.reps.LeaveReps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/11 16:59
 */
@RestController
@RequestMapping("/api")
@Api(tags = "请假相关")
public class LeaveController {

    @Autowired
    private LeaveApi leaveApi;

    @PostMapping(value = "createLeave")
    @ApiOperation(value = "申请请假")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "classId", required = true),
            @ApiImplicitParam(paramType = "query", name = "studentId", required = true),
            @ApiImplicitParam(paramType = "query", name = "leaveReason", required = true),
            @ApiImplicitParam(paramType = "query", name = "leaveStatus", required = true),
            @ApiImplicitParam(paramType = "query", name = "leaveStartTime", required = true),
            @ApiImplicitParam(paramType = "query", name = "leaveEndTime", required = true),
    })
    @ResponseBody
    public Boolean createLeave(Leave leave) {
        leaveApi.insertLeave(leave);
        return true;
    }

    @PostMapping(value = "selectMyLeaveList")
    @ApiOperation(value = "查看请假列表(本人)")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "studentId", required = true)
    })
    @ResponseBody
    public List<LeaveReps> selectMyLeaveList(@Param("studentId") String studentId){
       return leaveApi.selectLeaveByUserId(studentId);
    }

    @PostMapping(value = "selectClassLeaveList")
    @ApiOperation(value = "查看请假列表(班级)")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "classId", required = true),
    })
    @ResponseBody
    public List<Leave> selectClassLeaveList(@Param("classId") String classId){
        return leaveApi.selectLeaveByClassId(classId);
    }

    @PostMapping(value = "selectLeaveInfo")
    @ApiOperation(value = "查看请假内容")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "leaveId", required = true),
    })
    @ResponseBody
    public LeaveInfoReps selectLeaveInfo(@Param("leaveId") String leaveId){
        return leaveApi.selectLeaveByLeaveId(leaveId);
    }

    @PostMapping(value = "updateLeave")
    @ApiOperation(value = "同意或者拒绝请假")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "leaveId", required = true),
            @ApiImplicitParam(paramType = "query", name = "leaveStatus", required = true),
    })
    @ResponseBody
    public Boolean updateLeave(Leave leave){
        leaveApi.updateLeave(leave);
        return true;
    }
}
