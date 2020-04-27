package com.yhf.xuedaoqian.service;

import com.yhf.xuedaoqian.api.LeaveApi;
import com.yhf.xuedaoqian.dao.ClassStudentsDao;
import com.yhf.xuedaoqian.dao.LeaveDao;
import com.yhf.xuedaoqian.dao.SchoolClassDao;
import com.yhf.xuedaoqian.dao.WXUserDao;
import com.yhf.xuedaoqian.model.Leave;
import com.yhf.xuedaoqian.model.SchoolClass;
import com.yhf.xuedaoqian.model.WXUser;
import com.yhf.xuedaoqian.model.reps.LeaveInfoReps;
import com.yhf.xuedaoqian.model.reps.LeaveReps;
import com.yhf.xuedaoqian.util.ToolUtil;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/11 16:40
 */
@Service
public class LeaveService implements LeaveApi {
    private ClassStudentsDao studentsDao;
    private LeaveDao leaveDao;
    private SchoolClassDao classDao;
    private WXUserDao userDao;

    @Autowired
    public LeaveService(ClassStudentsDao studentsDao, LeaveDao leaveDao,
                        SchoolClassDao classDao, WXUserDao userDao) {
        this.studentsDao = studentsDao;
        this.leaveDao = leaveDao;
        this.classDao = classDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertLeave(Leave leave) {
        Assert.notNull(leave.getClassId(), "班级Id不能为空");
        Assert.notNull(leave.getStudentId(), "学生Id不能为空");
        Assert.notNull(leave.getLeaveReason(), "请假理由不能为空");
        WXUser user = userDao.selectUserByUserId(leave.getStudentId());
        if (user == null) {
            throw new RuntimeException("请输入正确的用户Id");
        } else {
            leave.setStudentName(user.getUserName());
        }
        SchoolClass schoolClass = classDao.selectClassInfo(leave.getClassId());
        if (schoolClass == null) {
            throw new RuntimeException("请输入正确的班级Id");
        }
        leave.setLeaveId(ToolUtil.getUUid());
        int daysBetween = (int) ((leave.getLeaveStartTime().getTime() - leave.getLeaveEndTime().getTime() + 1000000) / 86400000);
        leave.setLeaveDays(daysBetween == 0 ? 1 : daysBetween);
        leave.setCreateAt(new Date());
        leave.setUpdateAt(new Date());
        leaveDao.insertLeave(leave);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLeave(Leave leave) {
        Assert.notNull(leave.getLeaveId(), "请假Id不能为空");
        Leave leaveDb = leaveDao.selectLeaveByLeaveId(leave.getLeaveId());
        if (leaveDb == null) {
            throw new RuntimeException("请输入正确的请假表Id");
        }
        leave.setUpdateAt(new Date());
        leaveDao.updateLeave(leave);
    }

    @Override
    public LeaveInfoReps selectLeaveByLeaveId(String leaveId) {
        Assert.notNull(leaveId, "请假Id不能为空");
        Leave leave = leaveDao.selectLeaveByLeaveId(leaveId);
        LeaveInfoReps leaveInfoReps = new LeaveInfoReps();
        BeanUtils.copyProperties(leave, leaveInfoReps);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String leaveStartTime = dateFormat.format(leave.getLeaveStartTime());
        String leaveEndTime = dateFormat.format(leave.getLeaveEndTime());
        leaveInfoReps.setLeaveStartTime(leaveStartTime);
        leaveInfoReps.setLeaveEndTime(leaveEndTime);
        return leaveInfoReps;
    }

    @Override
    public List<LeaveReps> selectLeaveByUserId(String studentId) {
        Assert.notNull(studentId, "学生Id不能为空");
        WXUser user = userDao.selectUserByUserId(studentId);
        if (user == null) {
            throw new RuntimeException("请输入正确的用户Id");
        }
        return leaveDao.selectLeaveByUserId1(studentId);
    }

    @Override
    public List<Leave> selectLeaveByClassId(String classId) {
        Assert.notNull(classId, "班级Id不能为空");
        SchoolClass schoolClass = classDao.selectClassInfo(classId);
        if (schoolClass == null) {
            throw new RuntimeException("请输入正确的班级Id");
        }
        return leaveDao.selectLeaveByClassId(classId);
    }
}
