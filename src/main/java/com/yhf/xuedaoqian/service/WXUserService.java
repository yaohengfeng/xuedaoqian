package com.yhf.xuedaoqian.service;

import com.alibaba.fastjson.JSONObject;
import com.yhf.xuedaoqian.api.WXUserApi;
import com.yhf.xuedaoqian.dao.WXUserDao;
import com.yhf.xuedaoqian.model.WXLoginInfo;
import com.yhf.xuedaoqian.model.WXUser;
import com.yhf.xuedaoqian.util.OkHttpUtil;
import com.yhf.xuedaoqian.util.ToolUtil;
import io.jsonwebtoken.lang.Assert;
import net.minidev.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import java.util.Date;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/16 22:08
 */
@Service
public class WXUserService implements WXUserApi {
    private WXUserDao wxUserDao;

    private String jscode2session_url = "https://api.weixin.qq.com/sns/jscode2session";

    @Value("${wx_app_id}")
    private String appid;

    @Value("${wx_app_secret}")
    private String secret;

    private String grantType = "authorization_code";

    @Autowired
    public WXUserService(WXUserDao wxUserDao) {
        this.wxUserDao = wxUserDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String loginUser(String code)  {
        String userId = null;
        try {
            Assert.notNull(code, "用户请求code");
            WXLoginInfo loginJson=new WXLoginInfo();
            String url = jscode2session_url + "?appid=" + appid +
                    "&secret=" + secret + "&js_code=" + code + "&grant_type=" + grantType;
            String requestInfo = OkHttpUtil.get(url);
            System.out.println(requestInfo);
            loginJson =JSONObject.parseObject(requestInfo,WXLoginInfo.class);
            WXUser wxUser = wxUserDao.selectUserByOpenId(loginJson.getOpenid());
            if (wxUser != null && !"".equals(wxUser.getUserId())){
                System.out.println("数据库已经存在");
                userId=wxUser.getUserId();
            }else{
                WXUser newUser=new WXUser();
                userId=ToolUtil.getUUid();
                newUser.setUserId(userId);
                newUser.setUserName("");
                newUser.setWxOpenId(loginJson.getOpenid());
                newUser.setCreateTime(new Date());
                newUser.setUpdateTime(new Date());
                wxUserDao.insertUser(newUser);
            }
            System.out.println(loginJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(WXUser wxUser) {
        Assert.notNull(wxUser.getUserId(), "用户Id不能为空");
        WXUser wxUserDb = wxUserDao.selectUserByUserId(wxUser.getUserId());
        if (wxUserDb == null) {
            throw new RuntimeException("请输入正确的用户id");
        }
        WXUser newUser = new WXUser();
        newUser.setUserId(wxUser.getUserId());
        newUser.setUserName(wxUser.getUserName());
        newUser.setSchool(wxUser.getSchool());
        newUser.setSno(wxUser.getSno());
        newUser.setUpdateTime(new Date());
        wxUserDao.updateUser(newUser);
    }

    @Override
    public WXUser selectUserByUserId(String userId) {
        System.out.println("1564884ahi奥维德hi哦哈的"+userId);
        Assert.notNull(userId, "id不能为空");
        WXUser user = wxUserDao.selectUserByUserId(userId);
        if (user == null) {
            throw new RuntimeException("请输入正确的userId！");
        }
        return user;
    }

    @Override
    public Boolean selectUserInfo(String userId) {
        WXUser wxUser = wxUserDao.selectUserByUserId(userId);
        return (wxUser.getSchool() != null && !"".equals(wxUser.getSchool()))
                && (wxUser.getUserName() != null && !"".equals(wxUser.getUserName()))
                && (wxUser.getSno() != null && !"".equals(wxUser.getSno()));
    }
}
