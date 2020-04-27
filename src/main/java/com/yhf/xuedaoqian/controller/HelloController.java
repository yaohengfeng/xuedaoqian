package com.yhf.xuedaoqian.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/12 10:56
 */
@RestController
@RequestMapping("/hello")
@Api(tags = "测试一下api")
public class HelloController {
    @GetMapping("/test")
    @ApiOperation(value = "测试")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "测试", value = "hello word", required = true)
    })
    public String Hello() {
        return "hello word";
    }
}
