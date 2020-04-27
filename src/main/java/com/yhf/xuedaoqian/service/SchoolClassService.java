package com.yhf.xuedaoqian.service;

import com.yhf.xuedaoqian.api.SchoolClassApi;
import com.yhf.xuedaoqian.api.WXUserApi;
import com.yhf.xuedaoqian.dao.SchoolClassDao;
import com.yhf.xuedaoqian.model.SchoolClass;
import com.yhf.xuedaoqian.model.WXUser;
import com.yhf.xuedaoqian.util.ToolUtil;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/17 19:11
 */
@Service
public class SchoolClassService implements SchoolClassApi {

    private SchoolClassDao schoolClassDao;
    private WXUserApi wxUserApi;

    @Autowired
    public SchoolClassService(SchoolClassDao schoolClassDao, WXUserApi wxUserApi) {
        this.schoolClassDao = schoolClassDao;
        this.wxUserApi = wxUserApi;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createNewClass(SchoolClass schoolClass) {
        Assert.notNull(schoolClass.getClassName(), "班级名称不能为空");
        Assert.notNull(schoolClass.getTeacherId(), "创建人id不能为空");
        schoolClass.setClassId(ToolUtil.getUUid());
        WXUser wxUser = wxUserApi.selectUserByUserId(schoolClass.getTeacherId());
        schoolClass.setTeacherName(wxUser.getUserName());
        schoolClass.setCreateAt(new Date());
        schoolClass.setUpdateAt(new Date());
        schoolClassDao.insertClass(schoolClass);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateClassName(SchoolClass schoolClass) {
        Assert.notNull(schoolClass.getClassId(), "班级id不能为空");
        SchoolClass schoolClassDB = schoolClassDao.selectClassInfo(schoolClass.getClassId());
        if (schoolClassDB == null) {
            throw new RuntimeException("请输入正常的班级id");
        }
        schoolClassDao.updateClassByClassId(schoolClass);
    }

    @Override
    public SchoolClass selectClassInfo(String classId) {
        Assert.notNull(classId, "班级id不能为空");
        SchoolClass schoolClassDB = schoolClassDao.selectClassInfo(classId);
        if (schoolClassDB == null) {
            throw new RuntimeException("请输入正常的班级id");
        }
        return schoolClassDB;
    }

    @Override
    public List<SchoolClass> selectClassByTeacherId(String teacherId) {
        Assert.notNull(teacherId, "老师id不能为空");
        List<SchoolClass> schoolClassList = schoolClassDao.selectClassByTeacherId(teacherId);
        if (schoolClassList.isEmpty()) {
//            throw new RuntimeException("请输入正常的老师id");
            return null;
        }
        return schoolClassList;
    }

    @Override
    public List<SchoolClass> selectClassByStudentId(String studentId) {
        Assert.notNull(studentId, "学生id不能为空");
        List<SchoolClass> schoolClassList = schoolClassDao.selectClassByStudentId(studentId);
        if (schoolClassList.isEmpty()) {
//            throw new RuntimeException("请输入正常的学生id");
            return null;
        }
        return schoolClassList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteClass(String classId) {
        Assert.notNull(classId, "班级id不能为空");
        SchoolClass schoolClassDB = schoolClassDao.selectClassInfo(classId);
        if (schoolClassDB == null) {
            throw new RuntimeException("请输入正常的班级id");
        }
        schoolClassDao.deleteClass(classId);
    }

    @Override
    public List<SchoolClass> selectAllClass() {
        return schoolClassDao.selectClassList();
    }

    @Override
    public List<SchoolClass> selectClassByName(String className) {
        return schoolClassDao.selectClassInfoByName(className);
    }
}
