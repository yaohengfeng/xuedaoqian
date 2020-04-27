package com.yhf.xuedaoqian.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/13 21:52
 */
@Data
public class Curriculum implements Serializable {

    @ApiModelProperty(value = "课程id")
    private String curriculumId;

    @ApiModelProperty(value = "班级id")
    private String classId;

    @ApiModelProperty(value = "用户id")
    private String teacherId;

    @ApiModelProperty(value = "课程名字")
    private String curriculumName;

    @ApiModelProperty(value = "创建时间")
    private Date createAt;

    @ApiModelProperty(value = "更新时间")
    private Date updateAt;
}
