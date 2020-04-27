package com.yhf.xuedaoqian.controller;

import com.yhf.xuedaoqian.api.SchoolClassStudentsApi;
import com.yhf.xuedaoqian.model.SchoolClassStudents;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/19 20:55
 */
@RestController
@RequestMapping("/api")
@Api(tags = "班级人员相关")
public class SchoolClassStudentsController {

    @Autowired
    SchoolClassStudentsApi classStudentsApi;

    @PostMapping(value = "addClass")
    @ApiOperation(value = "[业务]-[班级人员]-加入班级")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "classId", required = true),
            @ApiImplicitParam(paramType = "query", name = "studentId", required = true)
    })
    @ResponseBody
    public boolean addClass(SchoolClassStudents classStudents) {
        classStudentsApi.addClass(classStudents);
        return true;
    }

    @PostMapping(value = "exitClass")
    @ApiOperation(value = "[业务]-[班级人员]-退出班级")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "studentId", required = true),
    })
    @ResponseBody
    public boolean exitClass(String studentId) {
        classStudentsApi.exitClass(studentId);
        return true;
    }

    @PostMapping(value = "selectClassStudents")
    @ApiOperation(value = "[业务]-[班级人员]-查询班级人员")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "classId", required = true),
    })
    @ResponseBody
    public List<SchoolClassStudents> selectClassStudents(@RequestParam String classId) {
        return classStudentsApi.selectClassStudents(classId);
    }

    @PostMapping(value = "checkOnClass")
    @ApiOperation(value = "[业务]-[班级人员]-查询是否已经加入班级")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "classId", required = true),
    })
    @ResponseBody
    public Boolean checkOnClass(SchoolClassStudents schoolClassStudents) {
        return classStudentsApi.checkOnClass(schoolClassStudents);
    }
}
