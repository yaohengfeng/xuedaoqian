package com.yhf.xuedaoqian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/12 12:15
 */
@Data
@ApiModel("班级人员")
public class SchoolClassStudents implements Serializable {

    @NotNull
    @ApiModelProperty(value = "id")
    private String classStudentId;

    @NotNull
    @ApiModelProperty(value = "班级id")
    private String classId;

    @NotNull
    @ApiModelProperty(value = "学生id")
    private String studentId;

    @NotNull
    @ApiModelProperty(value = "学生姓名")
    private String studentName;

    @ApiModelProperty(value = "签到总次数")
    private Integer signInCount = 0;

    @ApiModelProperty(value = "签到状态：0-未签到，1-已签到")
    private Integer signState = 0;

    @ApiModelProperty(value = "创建时间")
    private Date createAt;

    @ApiModelProperty(value = "更新时间")
    private Date updateAt;
}
