package com.yhf.xuedaoqian.controller;

import com.yhf.xuedaoqian.api.SchoolClassApi;
import com.yhf.xuedaoqian.model.SchoolClass;
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

import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/18 19:02
 */
@RestController
@RequestMapping("/api")
@Api(tags = "班级相关")
public class SchoolClassController {
    @Autowired
    SchoolClassApi schoolClassApi;

    @PostMapping(value = "createClass")
    @ApiOperation(value = "创建新的班级")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "className", required = true),
            @ApiImplicitParam(paramType = "query", name = "teacherId", required = true)
    })
    @ResponseBody
    public Boolean createSchoolClass(SchoolClass schoolClass) {
        schoolClassApi.createNewClass(schoolClass);
        return true;
    }

    @PostMapping(value = "selectClassInfo")
    @ApiOperation(value = "根据班级Id查询班级信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "classId", required = true),
    })
    @ResponseBody
    public SchoolClass selectClassInfo(@RequestParam String classId) {
        return schoolClassApi.selectClassInfo(classId);
    }

    @PostMapping(value = "deleteClass")
    @ApiOperation(value = "根据班级Id删除班级")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "classId", required = true),
    })
    @ResponseBody
    public Boolean deleteClass(@RequestParam String classId) {
        schoolClassApi.deleteClass(classId);
        return true;
    }

    @PostMapping(value = "selectMyCreateClass")
    @ApiOperation(value = "查询我创建的班级")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "teacherId", required = true),
    })
    @ResponseBody
    public List<SchoolClass> selectMyCreateClass(@RequestParam String teacherId) {
        return schoolClassApi.selectClassByTeacherId(teacherId);
    }

    @PostMapping(value = "selectMyAddClass")
    @ApiOperation(value = "查询我加入的班级")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "studentId", required = true),
    })
    @ResponseBody
    public List<SchoolClass> selectMyAddClass(@RequestParam String studentId) {
        return schoolClassApi.selectClassByStudentId(studentId);
    }

    @PostMapping(value = "searchClass")
    @ApiOperation(value = "搜索班级")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "className", required = true),
    })
    @ResponseBody
    public List<SchoolClass> searchClass(@RequestParam String className) {
        return schoolClassApi.selectClassByName(className);
    }

    @GetMapping(value = "selectAllClass")
    @ApiOperation(value = "查询所有班级")
    @ResponseBody
    public List<SchoolClass> selectAllClass(String value) {
        return schoolClassApi.selectAllClass();
    }
}
