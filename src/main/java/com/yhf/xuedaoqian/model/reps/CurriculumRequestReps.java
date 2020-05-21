package com.yhf.xuedaoqian.model.reps;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/24 13:05
 */
@Data
public class CurriculumRequestReps implements Serializable {
    private String teacherId;
    private String studentId;
    private String weekDay;
}
