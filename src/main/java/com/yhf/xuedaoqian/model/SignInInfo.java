package com.yhf.xuedaoqian.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/12 14:27
 */
@Data
public class SignInInfo implements Serializable {
    private String signInInfoId;
    private String signId;
    private String curriculumId;
    private String studentId;
    private Integer signFlag=0;
    private Date createAt;
    private Date updateAt;

}
