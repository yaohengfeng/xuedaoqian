package com.yhf.xuedaoqian.model.reps;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/22 14:58
 */
@Data
public class SignInReps implements Serializable {

    private String signId;
    private String classId;
    private String signInCode;
    private String studentId;
}
