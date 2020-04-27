package com.yhf.xuedaoqian.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/12 12:49
 */
@Data
public class Leave implements Serializable {

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
    private Integer leaveStatus=1;

    @ApiModelProperty(value = "请假开始时间")
    private Date leaveStartTime;

    @ApiModelProperty(value = "请假结束时间")
    private Date leaveEndTime;

    @ApiModelProperty(value = "请假天数")
    private Integer leaveDays;

    @ApiModelProperty(value = "创建时间")
    private Date createAt;

    @ApiModelProperty(value = "更新时间")
    private Date updateAt;

}
