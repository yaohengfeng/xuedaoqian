package com.yhf.xuedaoqian.service;

import com.yhf.xuedaoqian.XuedaoqianApplication;
import com.yhf.xuedaoqian.dao.ClassStudentsDao;
import com.yhf.xuedaoqian.dao.CurriculumDao;
import com.yhf.xuedaoqian.dao.SchoolClassDao;
import com.yhf.xuedaoqian.dao.SignDao;
import com.yhf.xuedaoqian.dao.SignInInfoDao;
import com.yhf.xuedaoqian.model.Curriculum;
import com.yhf.xuedaoqian.model.KaoQinLv;
import com.yhf.xuedaoqian.model.SchoolClass;
import com.yhf.xuedaoqian.model.SchoolClassStudents;
import com.yhf.xuedaoqian.model.SignInInfo;
import com.yhf.xuedaoqian.model.reps.CurriculumReps;
import com.yhf.xuedaoqian.model.reps.CurriculumTimeReps;
import com.yhf.xuedaoqian.model.reps.KaoQinReps;
import com.yhf.xuedaoqian.model.reps.SignInfoReps;
import com.yhf.xuedaoqian.util.ToolUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/11 12:19
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = XuedaoqianApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClassStudentDaoTest {

    private ClassStudentsDao classStudentsDao;
    private SchoolClassDao classDao;
    private SignDao signDao;
    private SignInInfoDao signInInfoDao;
    private CurriculumDao curriculumDao;

    @Autowired
    public ClassStudentDaoTest(ClassStudentsDao classStudentsDao, SignInInfoDao signInInfoDao,
                               SignDao signDao, CurriculumDao curriculumDao, SchoolClassDao classDao) {
        this.classStudentsDao = classStudentsDao;
        this.signDao = signDao;
        this.signInInfoDao = signInInfoDao;
        this.curriculumDao = curriculumDao;
        this.classDao = classDao;
    }

    @Test
    void createSign() {
        String singId = ToolUtil.getUUid();
        List<SchoolClassStudents> schoolClassStudents = classStudentsDao.selectUserByClassId("7f01256c-e8f1-4153-8125-6ce8f1615399");
        for (SchoolClassStudents s : schoolClassStudents) {
            SignInInfo signInInfo = new SignInInfo();
            signInInfo.setSignInInfoId(ToolUtil.getUUid());
            BeanUtils.copyProperties(s, signInInfo);
            signInInfo.setSignId(singId);
            signInInfo.setCreateAt(new Date());
            signInInfo.setUpdateAt(new Date());
            signInInfoDao.insertSignInfo(signInInfo);
        }
        System.out.println(schoolClassStudents);
    }

    @Test
    void selectSignInfo() {
        List<SignInfoReps> signInfoReps = signInInfoDao.selectSignInfo("44afdabb-ef84-4d80-afda-bbef847d80b0");
        System.out.println(signInfoReps);
    }

    @Test
    void selceteSign() {
//        String s = signDao.selectSignIdByClassId("7f01256c-e8f1-4153-8125-6ce8f1615399");
//        System.out.println(s);
//        List<SchoolClass> schoolClassList = classDao.selectClassList();
//        System.out.println(schoolClassList);
        List<SchoolClass> schoolClassList1 = classDao.selectClassInfoByName("");
        System.out.println(schoolClassList1);
    }

    @Test
    void signIn() {
        signInInfoDao.updateSignFlag("44afdabb-ef84-4d80-afda-bbef847d80b0", "9044f5fc-3305-45ea-84f5-fc330595ea4f");


//        classStudentsDao.updateSignInFlagByClassIdAndStudentId("7f01256c-e8f1-4153-8125-6ce8f1615399", "1036ea77-ef4c-449e-b6ea-77ef4cb49ece");
    }

    @Test
    void getWeek() {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String currSun = dateFm.format(date);
        System.out.println(currSun);

        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        System.out.println(w);
    }

    @Test
    void creatNewCurriculum() {
        Curriculum curriculum = new Curriculum();
        curriculum.setCurriculumId(ToolUtil.getUUid());
        curriculum.setCurriculumName("java程序与开发");
        curriculum.setClassId("7f01256c-e8f1-4153-8125-6ce8f1615399");
        curriculum.setTeacherId("912fb893-ff");
        curriculum.setCreateAt(new Date());
        curriculum.setUpdateAt(new Date());
        curriculumDao.insertCurriculum(curriculum);
    }

    @Test
    void selectCurriculum() {
        String classId = "7f01256c-e8f1-4153-8125-6ce8f1615399";
        List<Curriculum> curricula = curriculumDao.selectCurriculumListByClassId(classId);
        System.out.println(curricula);
    }

    @Test
    void selectCurriculumInfo() {
//        CurriculumReps curriculumReps = curriculumDao.selectCurriculumRepsInfo("f6bd12e1-99a5-42fe-bd12-e199a5e2fe3c");
//        System.out.println(curriculumReps);
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String currSun = dateFm.format(date);
        System.out.println(currSun);

        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        System.out.println(weekDays[w]);
//        List<CurriculumTimeReps> curriculumList = curriculumDao.selectAllCurriculumTimeRepsByTeacherId("912fb893-ff", weekDays[w]);
        List<CurriculumTimeReps> curriculumList = curriculumDao.selectAllCurriculumTimeRepsByStudentId("b8097f3a-6901-432b-897f-3a6901f32b76", "星期三");
        System.out.println(curriculumList);
    }

    @Test
    void selectSign() {
        Integer signNum = signDao.countSignNum("f6bd12e1-99a5-42fe-bd12-e199a5e2fe3c");
        System.out.println(signNum);
        List<KaoQinReps> kaoQinReps = signInInfoDao.selectSignSuccessNum("f6bd12e1-99a5-42fe-bd12-e199a5e2fe3c");
        System.out.println(kaoQinReps);
        List<KaoQinLv> kaoQinLvList = new ArrayList<>();
        for (KaoQinReps k : kaoQinReps) {
            KaoQinLv kaoQinLv = new KaoQinLv();
            kaoQinLv.setStudentName(k.getUserName());
            kaoQinLv.setPercentage(signNum != 0 ? ((k.getSuccessSignNum() / (float) signNum) * 100 + "%") : "0");
            kaoQinLvList.add(kaoQinLv);
        }
        System.out.println(kaoQinLvList);
    }

    @Test
    void selectSign1() {
        Integer signNum = signDao.countSignNum("f6bd12e1-99a5-42fe-bd12-e199a5e2fe3c");
        List<SchoolClassStudents> schoolClassStudentsList=classStudentsDao.selectUserByClassId("7f01256c-e8f1-4153-8125-6ce8f1615399");
        List<KaoQinLv> kaoQinLvList = new ArrayList<>();
        for (SchoolClassStudents s : schoolClassStudentsList) {
            KaoQinLv kaoQinLv = new KaoQinLv();
            kaoQinLv.setStudentName(s.getStudentName());
            Integer stuSignNum = signInInfoDao.selectSignSuccessNumByStudentId(s.getStudentId());
            kaoQinLv.setPercentage(signNum != 0 ? ((stuSignNum / (float) signNum) * 100 + "%") : "100%");
            kaoQinLvList.add(kaoQinLv);
        }
        System.out.println(kaoQinLvList);
    }
}
