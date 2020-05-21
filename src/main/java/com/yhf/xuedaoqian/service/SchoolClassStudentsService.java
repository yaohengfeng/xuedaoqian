package com.yhf.xuedaoqian.service;

import com.yhf.xuedaoqian.api.SchoolClassStudentsApi;
import com.yhf.xuedaoqian.dao.ClassStudentsDao;
import com.yhf.xuedaoqian.dao.SchoolClassDao;
import com.yhf.xuedaoqian.dao.WXUserDao;
import com.yhf.xuedaoqian.model.SchoolClass;
import com.yhf.xuedaoqian.model.SchoolClassStudents;
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
 * @date 2020/3/19 20:36
 */
@Service
public class SchoolClassStudentsService implements SchoolClassStudentsApi {

    SchoolClassDao schoolClassDao;

    ClassStudentsDao classStudentsDao;

    WXUserDao wxUserDao;

    @Autowired
    public SchoolClassStudentsService(SchoolClassDao schoolClassDao, ClassStudentsDao classStudentsDao,
                                      WXUserDao wxUserDao) {
        this.schoolClassDao = schoolClassDao;
        this.classStudentsDao = classStudentsDao;
        this.wxUserDao = wxUserDao;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addClass(SchoolClassStudents classStudents) {
        Assert.notNull(classStudents.getClassId(), "班级Id不能为空");
        Assert.notNull(classStudents.getStudentId(), "用户Id不能为空");
        String scId = classStudentsDao.selectScIdByClassIdAndStudentId(classStudents.getClassId(), classStudents.getStudentId());
        if (scId != null ) {
            throw new RuntimeException("你已经加入班级，请勿重复加入");
        }
        SchoolClass schoolClassDB = schoolClassDao.selectClassInfo(classStudents.getClassId());
        if (schoolClassDB.getTeacherId().equals(classStudents.getStudentId())){
            throw new RuntimeException("不能加入自己创建的课程");
        }
        if (schoolClassDB == null) {
            throw new RuntimeException("请输入正确的班级Id");
        }
        WXUser wxUserDB = wxUserDao.selectUserByUserId(classStudents.getStudentId());
        if (wxUserDB == null) {
            throw new RuntimeException("请输入正确的用户Id");
        }
        classStudents.setClassStudentId(ToolUtil.getUUid());
        classStudents.setCreateAt(new Date());
        classStudents.setStudentName(wxUserDB.getUserName());
        classStudents.setUpdateAt(new Date());
        classStudentsDao.insertClassStudent(classStudents);
    }

    @Override
    public void exitClass(String classStudentId) {
        Assert.notNull(classStudentId, "用户Id不能为空");
        SchoolClassStudents schoolClassStudents = classStudentsDao.selectUserByScsId(classStudentId);
        if (schoolClassStudents == null) {
            throw new RuntimeException("请输入正确的班级人员Id");
        }
        classStudentsDao.deleteClassUserByUserId(classStudentId);
    }

    @Override
    public List<SchoolClassStudents> selectClassStudents(String classId) {
        Assert.notNull(classId, "用户Id不能为空");
        SchoolClass schoolClassDB = schoolClassDao.selectClassInfo(classId);
        if (schoolClassDB == null) {
            throw new RuntimeException("请输入正确的班级Id");
        }
        return classStudentsDao.selectUserByClassId(classId);
    }

    @Override
    public Boolean checkOnClass(SchoolClassStudents classStudents) {
        String scId = classStudentsDao.selectScIdByClassIdAndStudentId(classStudents.getClassId(), classStudents.getStudentId());
        return scId != null;
    }
}
