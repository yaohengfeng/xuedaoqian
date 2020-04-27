package com.yhf.xuedaoqian.model.reps;

import lombok.Data;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/11 17:07
 */
@Data
public class LeaveReps {
    private String classId;
    private String studentId;
    private String className;
    private Integer leaveStatus;
    private String leaveId;
}
