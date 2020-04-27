package com.yhf.xuedaoqian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/12 12:08
 */
@Data
@ApiModel("班级")
public class SchoolClass implements Serializable {

    @ApiModelProperty(value = "班级id")
    private String classId;

    @ApiModelProperty(value = "班级名字")
    private String className;

    @ApiModelProperty(value = "老师名字")
    private String teacherName;

    @ApiModelProperty(value = "老师的Id")
    private String teacherId;

    @ApiModelProperty(value = "创建时间")
    private Date createAt;

    @ApiModelProperty(value = "更新时间")
    private Date updateAt;
}