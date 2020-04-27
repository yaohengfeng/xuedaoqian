package com.yhf.xuedaoqian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/18 17:07
 */
@Data
@ApiModel(description = "APP用户登录数据")
public class WXLoginInfo implements Serializable {

    @ApiModelProperty(value = "包括手机号在内的完整用户信息的加密数据")
    private String encryptedData;
    @ApiModelProperty(value = "加密算法的初始向量")
    private String iv;
    @ApiModelProperty(value = "用户请求code")
    private String code;
    @ApiModelProperty(value = "用户的openid")
    private String openid;
    @ApiModelProperty(value = "用户的session_key")
    private String session_key;
    @ApiModelProperty(value = "用户unionid")
    private String unionid;
    @ApiModelProperty(value = "返回的错误编码")
    private int errcode;
    @ApiModelProperty(value = "返回的错误信息")
    private String errmsg;
    @ApiModelProperty(value = "token")
    private String jwt;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "用户Id")
    private String userId;
    @ApiModelProperty(value = "用户昵称")
    private String userName;
    @ApiModelProperty(value = "性别")
    private Integer gender;

}
