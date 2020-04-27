package com.yhf.xuedaoqian.model.reps;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/12 14:38
 */
@Data
public class SignInfoReps implements Serializable {
    private String signInInfoId;
    private String signId;
    private String studentId;
    private String studentName;
    private Integer signFlag;
}
