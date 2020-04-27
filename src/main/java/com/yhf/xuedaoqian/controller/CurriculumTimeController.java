package com.yhf.xuedaoqian.controller;

import com.yhf.xuedaoqian.api.CurriculumTimeApi;
import com.yhf.xuedaoqian.model.CurriculumTime;
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
 * @date 2020/4/15 21:53
 */
@RestController
@RequestMapping("/api")
@Api(tags = "课程时间")
public class CurriculumTimeController {

    @Autowired
    private CurriculumTimeApi curriculumTimeApi;

    @PostMapping(value = "createCurriculumTime")
    @ApiOperation(value = "创建新的课程时间")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "curriculumId", required = true),
            @ApiImplicitParam(paramType = "query", name = "weekDay", required = true),
            @ApiImplicitParam(paramType = "query", name = "startTime", required = true),
            @ApiImplicitParam(paramType = "query", name = "endTime", required = true)
    })
    @ResponseBody
    public Boolean createCurriculumTime(CurriculumTime curriculumTime) {
        curriculumTimeApi.createNewCurriculumTime(curriculumTime);
        return true;
    }

    @PostMapping(value = "updateCurriculumTime")
    @ApiOperation(value = "创建新的课程时间")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "curriculumId", required = true),
            @ApiImplicitParam(paramType = "query", name = "weekDay", required = true),
            @ApiImplicitParam(paramType = "query", name = "startTime", required = true),
            @ApiImplicitParam(paramType = "query", name = "endTime", required = true)
    })
    @ResponseBody
    public Boolean updateCurriculumTime(CurriculumTime curriculumTime) {
        curriculumTimeApi.updateCurriculumTimeInfo(curriculumTime);
        return true;
    }

    @PostMapping(value = "selectCurriculumTimeList")
    @ApiOperation(value = "查询班级内的课程时间表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "curriculumId", required = true),
    })
    @ResponseBody
    public List<CurriculumTime> selectCurriculumTimeList(String curriculumId) {
        return curriculumTimeApi.selectCurriculumTimeList(curriculumId);
    }

    @PostMapping(value = "selectCurriculumTimeInfo")
    @ApiOperation(value = "查询班级内的课程时间信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "curriculumId", required = true),
    })
    @ResponseBody
    public CurriculumTime selectCurriculumTimeInfo(String curriculumTimeId) {
        return curriculumTimeApi.selectCurriculumTimeInfo(curriculumTimeId);
    }

    @PostMapping(value = "deleteCurriculumTime")
    @ApiOperation(value = "删除班级内的课程的时间")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "curriculumId", required = true),
    })
    @ResponseBody
    public boolean deleteCurriculumTime(String curriculumTimeId) {
        curriculumTimeApi.deleteCurriculumTime(curriculumTimeId);
        return true;
    }
}
