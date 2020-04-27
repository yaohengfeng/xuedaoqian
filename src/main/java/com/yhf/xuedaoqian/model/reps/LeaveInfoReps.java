package com.yhf.xuedaoqian.model.reps;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/18 15:33
 */
@Data
public class LeaveInfoReps {
    @ApiModelProperty(value = "请假表id")
    private String leaveId;

    @ApiModelProperty(value = "班级id")
    private String classId;

    @ApiModelProperty(value = "申请人id")
    private String studentId;

    @ApiModelProperty(value = "申请人姓名")
    private String studentName;

    @ApiModelProperty(value = "请假理由")
    private String leaveReason;

    @ApiModelProperty(value = "申请状态：0-草稿，1-申请中，2-已通过，3-拒绝")
    private Integer leaveStatus = 1;

    @ApiModelProperty(value = "请假开始时间")
    private String leaveStartTime;

    @ApiModelProperty(value = "请假结束时间")
    private String leaveEndTime;

    @ApiModelProperty(value = "请假天数")
    private Integer leaveDays;
}
