package com.yhf.xuedaoqian.model.reps;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/14 22:17
 */
@Data
public class CurriculumReps implements Serializable {
    private String curriculumId;
    private String curriculumName;
    private String teacherId;
    private String teacherName;
}
