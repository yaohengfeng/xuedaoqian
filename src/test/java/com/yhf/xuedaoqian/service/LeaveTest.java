package com.yhf.xuedaoqian.service;

import com.yhf.xuedaoqian.XuedaoqianApplication;
import com.yhf.xuedaoqian.dao.LeaveDao;
import com.yhf.xuedaoqian.model.Leave;
import com.yhf.xuedaoqian.util.ToolUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/11 16:03
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = XuedaoqianApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LeaveTest {
    @Autowired
    private LeaveDao leaveDao;

//    @Autowired
//    public LeaveTest(LeaveDao leaveDao) {
//        this.leaveDao = leaveDao;
//    }

    @Test
    void insertLeave() throws ParseException {
        Leave leave=new Leave();
        leave.setLeaveId(ToolUtil.getUUid());
        leave.setClassId("7f01256c-e8f1-4153-8125-6ce8f1615399");
        leave.setStudentId("9044f5fc-3305-45ea-84f5-fc330595ea4f");
        leave.setStudentName("王五");
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse("2020-04-11");
        Date endDate = dateFormat.parse("2020-04-13");
        leave.setLeaveStartTime(startDate);
        leave.setLeaveEndTime(endDate);
        int daysBetween = (int)((endDate.getTime() - startDate.getTime() + 1000000) / 86400000);
        leave.setLeaveDays(daysBetween);
        leave.setLeaveReason("感冒，想请假！");
        leave.setCreateAt(new Date());
        leave.setUpdateAt(new Date());
        leaveDao.insertLeave(leave);
    }

    @Test
    void selectMineLeaveList(){
        List<Leave> leaves = leaveDao.selectLeaveByUserId("1036ea77-ef4c-449e-b6ea-77ef4cb49ece", "7f01256c-e8f1-4153-8125-6ce8f1615399");
        System.out.println(leaves);
    }

    @Test
    void selectLeaveList(){
        List<Leave> leaves = leaveDao.selectLeaveByClassId("7f01256c-e8f1-4153-8125-6ce8f1615399");
        System.out.println(leaves);
    }
}
