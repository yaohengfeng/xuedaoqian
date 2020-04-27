package com.yhf.xuedaoqian.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/12 12:56
 */
@Data
public class Course implements Serializable {

    @ApiModelProperty(value = "课程id")
    private String courseId;

    @ApiModelProperty(value = "班级id")
    private String classId;

    @ApiModelProperty(value = "用户id")
    private String teacherId;

    @ApiModelProperty(value = "课程名字")
    private String courseName;

    @ApiModelProperty(value = "课程开始时间")
    private String courseStartTime;

    @ApiModelProperty(value = "课程结束时间")
    private String courseEndTime;

    @ApiModelProperty(value = "周几的课")
    private Integer weekDay;

    @ApiModelProperty(value = "创建时间")
    private Date createAt;

    @ApiModelProperty(value = "更新时间")
    private Date updateAt;
}
