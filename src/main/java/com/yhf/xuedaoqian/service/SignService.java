package com.yhf.xuedaoqian.service;

import com.yhf.xuedaoqian.api.SignApi;
import com.yhf.xuedaoqian.dao.ClassStudentsDao;
import com.yhf.xuedaoqian.dao.CurriculumDao;
import com.yhf.xuedaoqian.dao.LeaveDao;
import com.yhf.xuedaoqian.dao.SchoolClassDao;
import com.yhf.xuedaoqian.dao.SignDao;
import com.yhf.xuedaoqian.dao.SignInInfoDao;
import com.yhf.xuedaoqian.dao.WXUserDao;
import com.yhf.xuedaoqian.model.Curriculum;
import com.yhf.xuedaoqian.model.KaoQinLv;
import com.yhf.xuedaoqian.model.Leave;
import com.yhf.xuedaoqian.model.SchoolClass;
import com.yhf.xuedaoqian.model.SchoolClassStudents;
import com.yhf.xuedaoqian.model.Sign;
import com.yhf.xuedaoqian.model.SignInInfo;
import com.yhf.xuedaoqian.model.WXUser;
import com.yhf.xuedaoqian.model.reps.KaoQinReps;
import com.yhf.xuedaoqian.model.reps.SignInReps;
import com.yhf.xuedaoqian.model.reps.SignInfoReps;
import com.yhf.xuedaoqian.model.reps.SignReps;
import com.yhf.xuedaoqian.model.reps.SignTimeReps;
import com.yhf.xuedaoqian.util.ToolUtil;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/20 21:30
 */
@Service
public class SignService implements SignApi {

    private SignDao signDao;

    private SchoolClassDao schoolClassDao;

    private WXUserDao userDao;

    private ClassStudentsDao studentsDao;

    private SignInInfoDao signInInfoDao;

    private CurriculumDao curriculumDao;

    private LeaveDao leaveDao;

    @Autowired
    public SignService(SignDao signDao, SchoolClassDao schoolClassDao, SignInInfoDao signInInfoDao,
                       WXUserDao userDao, ClassStudentsDao studentsDao, CurriculumDao curriculumDao,
                       LeaveDao leaveDao) {
        this.signDao = signDao;
        this.schoolClassDao = schoolClassDao;
        this.userDao = userDao;
        this.studentsDao = studentsDao;
        this.signInInfoDao = signInInfoDao;
        this.curriculumDao = curriculumDao;
        this.leaveDao = leaveDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createSign(Sign sign) {
//        Assert.notNull(sign.getClassId(), "班级Id不能为空");
        Assert.notNull(sign.getCreateUserId(), "用户Id不能为空");
        Assert.notNull(sign.getSignInCode(), "签到码不能为空");
        Assert.notNull(sign.getCurriculumId(), "课程id不能为空");
        Curriculum curriculum = curriculumDao.selectCurriculumInfo(sign.getCurriculumId());
        if (curriculum == null) {
            throw new RuntimeException("请输入正确的课程Id");
        }
//        SchoolClass schoolClassDb = schoolClassDao.selectClassInfo(curriculum.getClassId());
//        System.out.println(sign.getClassId());
        String singId;
//        if (schoolClassDb == null) {
//            throw new RuntimeException("请输入正确的班级Id");
//        }
        WXUser wxUserDb = userDao.selectUserByUserId(sign.getCreateUserId());
        if (wxUserDb == null) {
            throw new RuntimeException("请输入正确的用户Id");
        }

        if (signDao.selectSignIdByCurriculumId(sign.getCurriculumId()) != null) {
            throw new RuntimeException("请结束之前的签到");
        } else {
            singId = ToolUtil.getUUid();
            sign.setSignId(singId);
            sign.setCreateAt(new Date());
            sign.setUpdateAt(new Date());
            sign.setSignFlag(1);
            signDao.insertSign(sign);
//            studentsDao.updateAllStudentSignInFlag(sign.getClassId());
            System.out.println(sign.getClassId());
            List<SchoolClassStudents> schoolClassStudents = studentsDao.selectUserByClassId(curriculum.getClassId());
            for (SchoolClassStudents s : schoolClassStudents) {
                SignInInfo signInInfo = new SignInInfo();
                signInInfo.setSignInInfoId(ToolUtil.getUUid());
                BeanUtils.copyProperties(s, signInInfo);
                signInInfo.setSignId(singId);
                signInInfo.setCurriculumId(sign.getCurriculumId());
                signInInfo.setCreateAt(new Date());
                signInInfo.setUpdateAt(new Date());
                signInInfoDao.insertSignInfo(signInInfo);
            }
            List<Leave> leaves = leaveDao.selectLeaveByClassId(curriculum.getClassId());
            for (Leave l:leaves) {
                if (l.getLeaveStartTime().before(new Date())&&(new Date().before(l.getLeaveEndTime()))&&l.getLeaveStatus()==2){
                    signInInfoDao.updateSignFlag1(sign.getSignId(),l.getStudentId(),5);
                }
            }
            System.out.println(singId);
            return singId;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startAndEndSignIn(Sign sign) {
        Assert.notNull(sign.getSignId(), "签到表Id不能为空");
        Assert.notNull(sign.getSignFlag(), "签到状态不能为空");
        Sign signDB = signDao.selectSignBySignId(sign.getSignId());
        if (signDB == null) {
            throw new RuntimeException("请输入正确的签到表Id");
        }
        sign.setUpdateAt(new Date());
        signDao.updateSingFlag(sign);
    }

    @Override
    public List<Sign> selectSignInHistory(String crruiculumId) {
        Assert.notNull(crruiculumId, "课程id不能为空");
        Curriculum curriculum = curriculumDao.selectCurriculumInfo(crruiculumId);
        if (curriculum == null) {
            throw new RuntimeException("请输入正确的课程Id");
        }

        return signDao.selectSignByCurriculumId(crruiculumId);
    }

    @Override
    public List<SignInfoReps> selectSignInfo(String signId) {
        Assert.notNull(signId, "签到表Id不能为空");
        return signInInfoDao.selectSignInfo(signId);
    }

    @Override
    public List<SignTimeReps> selectCurriculumSignList(String curriculumId) {
        Assert.notNull(curriculumId, "课程Id不能为空");
        if (curriculumDao.selectCurriculumInfo(curriculumId) == null) {
            throw new RuntimeException("请输入正确的课程id");
        }
        List<SignTimeReps> signTimeRepsList = new ArrayList<>();
        List<Sign> signs = signDao.selectSignByCurriculumId(curriculumId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        for (Sign sign : signs) {
            SignTimeReps signTimeReps = new SignTimeReps();
            BeanUtils.copyProperties(sign, signTimeReps);
            signTimeReps.setCreateAt(dateFormat.format(sign.getCreateAt()));
            signTimeRepsList.add(signTimeReps);
        }
        System.out.println(signTimeRepsList);
        return signTimeRepsList;
    }

    @Override
    public List<KaoQinLv> selectKaoQinLv(String classId, String curriculumId) {
        Assert.notNull(curriculumId, "课程Id不能为空");
        System.out.println(curriculumId);
        Curriculum curriculum=curriculumDao.selectCurriculumInfo(curriculumId);
        if (curriculum == null) {
            throw new RuntimeException("请输入正确的课程id");
        }
        Integer signNum = signDao.countSignNum(curriculumId);
        System.out.println(signNum);
        List<SchoolClassStudents> schoolClassStudentsList = studentsDao.selectUserByClassId(curriculum.getClassId());
        List<KaoQinLv> kaoQinLvList = new ArrayList<>();
        for (SchoolClassStudents s : schoolClassStudentsList) {
            KaoQinLv kaoQinLv = new KaoQinLv();
            kaoQinLv.setStudentName(s.getStudentName());
            Integer stuSignNum = signInInfoDao.selectSignSuccessNumByStudentId(s.getStudentId());
            kaoQinLv.setPercentage(signNum != 0 ? stuSignNum.equals(signNum) ? "100%" : ((stuSignNum / (float) signNum) * 100 + "%") : "100%");
            kaoQinLvList.add(kaoQinLv);
        }
        return kaoQinLvList;
    }

    @Override
    public void updateSignState2(SignReps signReps) {
        Assert.notNull(signReps.getSignId(), "签到Id不能为空");
        Assert.notNull(signReps.getStudentId(), "学生Id不能为空");
        System.out.println(signReps.getCurriculumId());
        Curriculum curriculum = curriculumDao.selectCurriculumInfo(signReps.getCurriculumId());

        Sign signDb = signDao.selectSignBySignId(signReps.getSignId());
        if (signDb == null) {
            throw new RuntimeException("请输入正确的签到Id");
        }
        if (signDb.getSignFlag() != 1) {
            throw new RuntimeException("签到已结束，不能签到！");
        }
        System.out.println(signReps.getSignId()+","+signReps.getStudentId()+","+signReps.getSignFlag());
        signInInfoDao.updateSignFlag1(signReps.getSignId(), signReps.getStudentId(),signReps.getSignFlag());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSignState(SignInReps signInReps) {
        Assert.notNull(signInReps.getSignId(), "签到Id不能为空");
        Assert.notNull(signInReps.getClassId(), "班级Id不能为空");
        Assert.notNull(signInReps.getStudentId(), "学生Id不能为空");
        Assert.notNull(signInReps.getSignInCode(), "签到码不能为空");
        Sign signDb = signDao.selectSignBySignId(signInReps.getSignId());
        if (signDb == null) {
            throw new RuntimeException("请输入正确的签到Id");
        }
        if (signDb.getSignFlag() != 1) {
            throw new RuntimeException("签到已结束，不能签到！");
        }
        if (!signDb.getSignInCode().equals(signInReps.getSignInCode())) {
            throw new RuntimeException("签到码不同，不能签到！");
        }
        studentsDao.updateSignInFlagByClassIdAndStudentId(signInReps.getClassId(), signInReps.getStudentId());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSignState1(SignReps signReps) {
        Assert.notNull(signReps.getSignId(), "签到Id不能为空");
        Assert.notNull(signReps.getStudentId(), "学生Id不能为空");
        Assert.notNull(signReps.getSignInCode(), "签到码不能为空");
        System.out.println(signReps.getCurriculumId());
        Curriculum curriculum = curriculumDao.selectCurriculumInfo(signReps.getCurriculumId());
        if (curriculum == null) {
            throw new RuntimeException("请输入正确的课程Id");
        }
        String sId = signInInfoDao.selectSignInfoStudentId(signReps.getStudentId());
        if (sId == null) {
            SchoolClassStudents schoolClassStudents = studentsDao.selectStudentInfoByStudentId(signReps.getStudentId(),curriculum.getClassId());
            SignInInfo signInInfo = new SignInInfo();
            signInInfo.setSignInInfoId(ToolUtil.getUUid());
            BeanUtils.copyProperties(schoolClassStudents, signInInfo);
            signInInfo.setSignId(signReps.getSignId());
            signInInfo.setCurriculumId(signReps.getCurriculumId());
            signInInfo.setCreateAt(new Date());
            signInInfo.setUpdateAt(new Date());
            signInInfoDao.insertSignInfo(signInInfo);
        }
        Sign signDb = signDao.selectSignBySignId(signReps.getSignId());
        if (signDb == null) {
            throw new RuntimeException("请输入正确的签到Id");
        }
        if (signDb.getSignFlag() != 1) {
            throw new RuntimeException("签到已结束，不能签到！");
        }
        if (!signDb.getSignInCode().equals(signReps.getSignInCode())) {
            throw new RuntimeException("签到码不同，不能签到！");
        }
        signInInfoDao.updateSignFlag(signReps.getSignId(), signReps.getStudentId());
    }

    @Override
    public String selectSignId(String curriculumId) {
        Assert.notNull(curriculumId, "课程id不能为空");
        return signDao.selectSignIdByCurriculumId(curriculumId);
    }
}
