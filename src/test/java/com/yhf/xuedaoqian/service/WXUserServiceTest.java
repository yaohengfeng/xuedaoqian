package com.yhf.xuedaoqian.service;

import com.yhf.xuedaoqian.XuedaoqianApplication;
import com.yhf.xuedaoqian.dao.WXUserDao;
import com.yhf.xuedaoqian.model.WXUser;
import com.yhf.xuedaoqian.util.ToolUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/12 21:19
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = XuedaoqianApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WXUserServiceTest {
    private WXUserDao wxUserDao;

    @Autowired
    public WXUserServiceTest(WXUserDao wxUserDao) {
        this.wxUserDao = wxUserDao;
    }

    @Test
    public void testInsertWxUser() {
        WXUser wxUser = new WXUser();
        wxUser.setUserId(ToolUtil.getUUid());
        wxUser.setUserName("王五");
        wxUser.setSno("1612255");
        wxUser.setSchool("重大城科");
        wxUser.setWxOpenId("hasopenid");
        wxUser.setCreateTime(new Date());
        wxUser.setUpdateTime(new Date());
        wxUserDao.insertUser(wxUser);
    }

    @Test
    void testSelectUserInfoByUserId() {
        WXUser wxUser = wxUserDao.selectUserByUserId("912fb893-ff");
        System.out.println(wxUser);
    }

    @Test
    void testUpdateUserInfoByUserId() {
        WXUser wxUser = wxUserDao.selectUserByUserId("912fb893-ff");
        System.out.println(wxUser);
    }
}
