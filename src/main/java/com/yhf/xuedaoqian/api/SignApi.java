package com.yhf.xuedaoqian.api;

import com.yhf.xuedaoqian.model.Sign;
import com.yhf.xuedaoqian.model.reps.SignInReps;
import com.yhf.xuedaoqian.model.reps.SignInfoReps;
import com.yhf.xuedaoqian.model.reps.SignReps;
import com.yhf.xuedaoqian.model.reps.SignTimeReps;

import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/20 21:23
 */
public interface SignApi {
    /**
     * @return String
     * @Description 创建签到表
     * @Param [sign]
     * @Author yaohengfeng
     * @Date 2020/3/20
     */
    String createSign(Sign sign);

    /**
     * @return void
     * @Description 开始或结束签到
     * @Param [signId, signFlag]
     * @Author yaohengfeng
     * @Date 2020/3/20
     */
    void startAndEndSignIn(Sign sign);

    /**
     * @return java.util.List<com.yhf.xuedaoqian.model.Sign>
     * @Description 查询班级创建签到历史
     * @Param [ClassId]
     * @Author yaohengfeng
     * @Date 2020/3/20
     */
    List<Sign> selectSignInHistory(String curriculumId);

    /**
    * @Description 签到
    * @Param [signInReps]
    * @return void
    * @Author yaohengfeng
    * @Date 2020/3/23
    */
    void updateSignState(SignInReps signInReps);

    /**
    * @Description 签到
    * @Param [signReps]
    * @return void
    * @Author yaohengfeng
    * @Date 2020/4/18
    */
    void updateSignState1(SignReps signReps);
    /**
    * @Description 根据课程Id查询正在签到的签到表id
    * @Param [classId]
    * @return java.lang.String
    * @Author yaohengfeng
    * @Date 2020/4/7
    */
    String selectSignId(String curriculumId);

    /**
    * @Description 根据签到Id查询签到信息
    * @Param [signId]
    * @return java.util.List<com.yhf.xuedaoqian.model.reps.SignInfoReps>
    * @Author yaohengfeng
    * @Date 2020/4/12
    */
    List<SignInfoReps> selectSignInfo(String signId);

    /**
    * @Description 根据课程id查询签到表
    * @Param [curriculumId]
    * @return java.util.List<com.yhf.xuedaoqian.model.Sign>
    * @Author yaohengfeng
    * @Date 2020/4/14
    */
    List<SignTimeReps> selectCurriculumSignList(String curriculumId);




}
