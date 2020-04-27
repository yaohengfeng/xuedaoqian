package com.yhf.xuedaoqian.model.reps;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/18 10:35
 */
@Data
public class SignReps implements Serializable {
    private String signId;
    private String studentId;
    private String signInCode;
}
