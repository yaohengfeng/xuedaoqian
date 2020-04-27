package com.yhf.xuedaoqian.api;

import com.yhf.xuedaoqian.model.WXLoginInfo;
import com.yhf.xuedaoqian.model.WXUser;
import org.springframework.stereotype.Service;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/16 21:46
 */
public interface WXUserApi {

    /**
     * @return void
     * @Description 用户登录
     * @Param [wxUser]
     * @Author yaohengfeng
     * @Date 2020/3/16
     */
    String loginUser(String code);

    /**
     * @return void
     * @Description 修改用户信息
     * @Param [wxUser]
     * @Author yaohengfeng
     * @Date 2020/3/16
     */
    void updateUser(WXUser wxUser);

    /**
     * @return com.yhf.xuedaoqian.model.WXUser
     * @Description 根据Id查询用户信息
     * @Param [userId]
     * @Author yaohengfeng
     * @Date 2020/3/16
     */
    WXUser selectUserByUserId(String userId);
}
