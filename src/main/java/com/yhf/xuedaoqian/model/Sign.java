package com.yhf.xuedaoqian.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/12 12:25
 */
@Data
public class Sign implements Serializable {

    @NotNull
    @ApiModelProperty(value = "签到表id")
    private String signId;

    @NotNull
    @ApiModelProperty(value = "班级id")
    private String classId;

    @NotNull
    @ApiModelProperty(value = "课程Id")
    private String curriculumId;

    @NotNull
    @ApiModelProperty(value = "创建人id")
    private String createUserId;

    @NotNull
    @ApiModelProperty(value = "签到码")
    private String signInCode;

    @ApiModelProperty(value = "签到状况：0-未开始,1-进行中,2-结束")
    private Integer signFlag = 0;

    @ApiModelProperty(value = "创建时间")
    private Date createAt;

    @ApiModelProperty(value = "更新时间")
    private Date updateAt;

}
