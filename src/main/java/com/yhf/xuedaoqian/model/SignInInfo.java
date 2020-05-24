package com.yhf.xuedaoqian.model;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "签到状况：0-未签到,1-已签到,2-早退,3-迟到,5-请假")
    private Integer signFlag=0;
    private Date createAt;
    private Date updateAt;

}
