package com.yhf.xuedaoqian.controller;

import com.yhf.xuedaoqian.api.CurriculumApi;
import com.yhf.xuedaoqian.model.Curriculum;
import com.yhf.xuedaoqian.model.reps.CurriculumReps;
import com.yhf.xuedaoqian.model.reps.CurriculumRequestReps;
import com.yhf.xuedaoqian.model.reps.CurriculumTimeReps;
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
 * @date 2020/4/14 20:54
 */
@RestController
@RequestMapping("/api")
@Api(tags = "课程相关")
public class CurriculumController {

    @Autowired
    private CurriculumApi curriculumApi;

    @PostMapping(value = "createCurriculum")
    @ApiOperation(value = "创建课程")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "classId", required = true),
            @ApiImplicitParam(paramType = "query", name = "teacherId", required = true),
            @ApiImplicitParam(paramType = "query", name = "curriculumName", required = true)
    })
    @ResponseBody
    public Boolean createCurriculum(Curriculum curriculum) {
        curriculumApi.createCurriculum(curriculum);
        return true;
    }

    @PostMapping(value = "selectCurriculumList")
    @ApiOperation(value = "查询班级内的课程")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "classId", required = true),
    })
    @ResponseBody
    public List<Curriculum> selectCurriculumList(String classId) {
        return curriculumApi.selectCurriculumList(classId);
    }


    @PostMapping(value = "selectCurriculumInfo")
    @ApiOperation(value = "查询课程详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "curriculumId", required = true),
    })
    @ResponseBody
    public CurriculumReps selectCurriculumInfo(String curriculumId) {
        return curriculumApi.selectCurriculumInfo(curriculumId);
    }

    @PostMapping(value = "selectAllCurriculumList")
    @ApiOperation(value = "查询课程列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "teacherId", required = true),
            @ApiImplicitParam(paramType = "query", name = "weekDay", required = true),
    })
    @ResponseBody
    public List<CurriculumTimeReps> selectAllCurriculumList(CurriculumRequestReps curriculumRequestReps) {
        return curriculumApi.selectCurriculumTimeByTeacherId(curriculumRequestReps);
    }

    @PostMapping(value = "deleteCurriculum")
    @ApiOperation(value = "删除课程详细")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "curriculumId", required = true),
    })
    @ResponseBody
    public boolean deleteCurriculum(String curriculumId) {
        curriculumApi.deleteCurriculum(curriculumId);
        return true;
    }

}
