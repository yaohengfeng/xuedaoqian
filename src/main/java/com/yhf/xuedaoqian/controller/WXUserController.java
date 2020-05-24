package com.yhf.xuedaoqian.controller;

import com.yhf.xuedaoqian.api.WXUserApi;
import com.yhf.xuedaoqian.model.WXUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/16 21:50
 */
@RestController
@RequestMapping("/api")
@Api(tags = "用户相关")
public class WXUserController {
    @Autowired
    private WXUserApi userApi;

    @PostMapping(value = "selectUserInfo")
    @ApiOperation(value = "根据用户Id查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", required = true)
    })
    @ResponseBody
    public WXUser selectUserInfo(@RequestParam String userId) {
        return userApi.selectUserByUserId(userId);
    }


    @PostMapping(value = "selectUserInfoBoolean")
    @ApiOperation(value = "根据用户Id查询信息是否补全")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", required = true)
    })
    @ResponseBody
    public Boolean selectUserInfoBoolean(@RequestParam String userId) {
        return userApi.selectUserInfo(userId);
    }

    @GetMapping(value = "wxUserLogin")
    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "code", required = true)
    })
    @ResponseBody
    public String  wxUserLogin(@RequestParam String code) {
        System.out.println(code);
        return userApi.loginUser(code);
    }

    @PostMapping(value = "updateUser")
    @ApiOperation(value = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", required = true),
            @ApiImplicitParam(paramType = "query", name = "userName", required = true),
            @ApiImplicitParam(paramType = "query", name = "sno", required = true),
            @ApiImplicitParam(paramType = "query", name = "school", required = true)
    })
    @ResponseBody
    public Boolean updateUser(WXUser user) {
        userApi.updateUser(user);
        return true;
    }
}
