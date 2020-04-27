package com.yhf.xuedaoqian.dao;

import com.yhf.xuedaoqian.model.WXUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/12 20:53
 */
@Mapper
public interface WXUserDao {
    void insertUser(WXUser wxUser);

    void updateUser(WXUser wxUser);

    WXUser selectUserByOpenId(String openId);

    WXUser selectUserByUserId(String userId);


}
