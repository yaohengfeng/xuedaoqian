package com.yhf.xuedaoqian.controller;

import com.yhf.xuedaoqian.api.SignApi;
import com.yhf.xuedaoqian.model.Sign;
import com.yhf.xuedaoqian.model.reps.SignInReps;
import com.yhf.xuedaoqian.model.reps.SignInfoReps;
import com.yhf.xuedaoqian.model.reps.SignReps;
import com.yhf.xuedaoqian.model.reps.SignTimeReps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/20 22:16
 */
@RestController
@RequestMapping("/api")
@Api(tags = "签到相关")
public class SignController {

    @Autowired
    private SignApi signApi;

    @PostMapping("createSignIn")
    @ApiOperation("创建签到")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "classId", required = true),
            @ApiImplicitParam(paramType = "query", name = "curriculumId", required = true),
            @ApiImplicitParam(paramType = "query", name = "createUserId", required = true),
            @ApiImplicitParam(paramType = "query", name = "signInCode", required = true)
    })
    @ResponseBody
    public String createSignIn(Sign sign) {
        return signApi.createSign(sign);
    }

    @PostMapping("changeSignFlag")
    @ApiOperation("开始或者结束签到1-开始 2-结束")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "signId", required = true),
            @ApiImplicitParam(paramType = "query", name = "signFlag", required = true)
    })
    @ResponseBody
    public Boolean changeSignFlag(Sign sign) {
        signApi.startAndEndSignIn(sign);
        return true;
    }

    @PostMapping("changeSignStats")
    @ApiOperation("人员签到")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "signId", required = true),
            @ApiImplicitParam(paramType = "query", name = "studentId", required = true),
            @ApiImplicitParam(paramType = "query", name = "signInCode", required = true)
    })
    @ResponseBody
    public Boolean changeSignStats(SignReps signReps) {
        System.out.println(signReps);
        signApi.updateSignState1(signReps);
        return true;
    }

    @PostMapping("selectSign")
    @ApiOperation("查询签到")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "curriculumId", required = true)
    })
    @ResponseBody
    public String selectSign(String curriculumId) {
        return signApi.selectSignId(curriculumId);
    }

    @PostMapping("selectSignList")
    @ApiOperation("查询签到")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "curriculumId", required = true)
    })
    @ResponseBody
    public List<SignTimeReps> selectSignList(String curriculumId) {
        return signApi.selectCurriculumSignList(curriculumId);
    }

    @PostMapping("selectSignInoList")
    @ApiOperation("查询签到")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "signId", required = true)
    })
    @ResponseBody
    public List<SignInfoReps> selectSignInoList(String signId) {
        return signApi.selectSignInfo(signId);
    }
}
