package com.yhf.xuedaoqian.model.reps;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/18 11:59
 */
@Data
public class SignTimeReps implements Serializable {

    @NotNull
    @ApiModelProperty(value = "签到表id")
    private String signId;

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
    private String createAt;
}
