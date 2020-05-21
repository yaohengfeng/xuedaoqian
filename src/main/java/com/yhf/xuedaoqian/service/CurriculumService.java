package com.yhf.xuedaoqian.service;

import com.yhf.xuedaoqian.api.CurriculumApi;
import com.yhf.xuedaoqian.dao.ClassStudentsDao;
import com.yhf.xuedaoqian.dao.CurriculumDao;
import com.yhf.xuedaoqian.dao.CurriculumTimeDao;
import com.yhf.xuedaoqian.dao.SchoolClassDao;
import com.yhf.xuedaoqian.model.Curriculum;
import com.yhf.xuedaoqian.model.SchoolClass;
import com.yhf.xuedaoqian.model.reps.CurriculumReps;
import com.yhf.xuedaoqian.model.reps.CurriculumRequestReps;
import com.yhf.xuedaoqian.model.reps.CurriculumTimeReps;
import com.yhf.xuedaoqian.util.ToolUtil;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/13 22:26
 */
@Service
public class CurriculumService implements CurriculumApi {

    private SchoolClassDao classDao;

    private ClassStudentsDao classStudentsDao;

    private CurriculumDao curriculumDao;

    private CurriculumTimeDao curriculumTimeDao;

    @Autowired
    public CurriculumService(SchoolClassDao classDao, ClassStudentsDao classStudentsDao,
                             CurriculumDao curriculumDao, CurriculumTimeDao curriculumTimeDao) {
        this.classDao = classDao;
        this.classStudentsDao = classStudentsDao;
        this.curriculumDao = curriculumDao;
        this.curriculumTimeDao = curriculumTimeDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createCurriculum(Curriculum curriculum) {
        Assert.notNull(curriculum.getClassId(), "班级id不能为空");
        Assert.notNull(curriculum.getTeacherId(), "教师Id不能为空");
        Assert.notNull(curriculum.getCurriculumName(), "课程名字不能为空");
        curriculum.setCurriculumId(ToolUtil.getUUid());
        curriculum.setCreateAt(new Date());
        curriculum.setUpdateAt(new Date());
        curriculumDao.insertCurriculum(curriculum);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCurriculum(Curriculum curriculum) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCurriculum(String curriculumId) {
        Assert.notNull(curriculumId, "课程Id不能为空");
        if (curriculumDao.selectCurriculumInfo(curriculumId) == null) {
            throw new RuntimeException("请输入正确的课程Id");
        }
        curriculumDao.deleteCurriculumByCurriculumId(curriculumId);
        curriculumTimeDao.deleteCurriculumTimeByCurriculumId(curriculumId);
    }

    @Override
    public List<Curriculum> selectCurriculumList(String classId) {
        Assert.notNull(classId, "班级Id不能为空");
        SchoolClass schoolClass = classDao.selectClassInfo(classId);
        if (schoolClass == null) {
            throw new RuntimeException("请输入正确班级Id");
        }
        return curriculumDao.selectCurriculumListByClassId(classId);
    }

    @Override
    public CurriculumReps selectCurriculumInfo(String curriculumId) {
        Assert.notNull(curriculumId, "课程Id不能为空");
        return curriculumDao.selectCurriculumRepsInfo(curriculumId);

    }

    @Override
    public List<CurriculumTimeReps> selectCurriculumTimeByTeacherId(CurriculumRequestReps curriculumRequestReps) {
//        Assert.notNull(curriculumRequestReps.getTeacherId(), "教师Id不能为空");
//        System.out.println(curriculumRequestReps.getWeekDay());
        if (curriculumRequestReps.getWeekDay() == null || "".equals(curriculumRequestReps.getWeekDay())) {
            curriculumRequestReps.setWeekDay(getWeekDay());
        }
        List<CurriculumTimeReps> curriculumTimeRepsList=new ArrayList<>();
        System.out.println(curriculumRequestReps.getWeekDay());
        if (curriculumRequestReps.getTeacherId() != null) {
            curriculumTimeRepsList= curriculumDao.selectAllCurriculumTimeRepsByTeacherId(curriculumRequestReps.getTeacherId(), curriculumRequestReps.getWeekDay());
        }
        else if(curriculumRequestReps.getStudentId() != null){
            curriculumTimeRepsList=curriculumDao.selectAllCurriculumTimeRepsByStudentId(curriculumRequestReps.getStudentId(), curriculumRequestReps.getWeekDay());
        }else{
            Assert.notNull(curriculumRequestReps.getTeacherId(), "教师Id不能为空");
            Assert.notNull(curriculumRequestReps.getTeacherId(), "学生Id不能为空");
        }
        return curriculumTimeRepsList;
    }


    private String getWeekDay() {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        return dateFm.format(date);
    }
}
