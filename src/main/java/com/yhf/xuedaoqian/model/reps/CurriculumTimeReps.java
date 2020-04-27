package com.yhf.xuedaoqian.model.reps;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/24 11:49
 */
@Data
public class CurriculumTimeReps implements Serializable {
    private String curriculumId;
    private String curriculumTimeId;
    private String classId;
    private String teacherId;
    private String className;
    private String curriculumName;
    private String weekDay;
    private String startTime;
    private String endTime;
}
