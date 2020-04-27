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
 * @date 2020/3/12 11:50
 */
@Data
@ApiModel("用户")
public class WXUser implements Serializable {

    @NotNull
    @ApiModelProperty(value = "用户主键")
    private String userId;
    @NotNull(message = "姓名不能为空")
    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "学号")
    private String sno;

    @ApiModelProperty(value = "学校")
    private String school;

    @NotNull
    @ApiModelProperty(value = "微信的openId")
    private String wxOpenId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
