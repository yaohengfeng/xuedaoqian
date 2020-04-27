package com.yhf.xuedaoqian.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/14 22:42
 */
@Data
public class CurriculumTime implements Serializable {

    private String curriculumTimeId;
    private String curriculumId;
    private String weekDay;
    private String startTime;
    private String endTime;
    private Date createAt;
    private Date updateAt;
}
