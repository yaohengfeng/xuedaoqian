package com.yhf.xuedaoqian.dao;

import com.yhf.xuedaoqian.model.SignInInfo;
import com.yhf.xuedaoqian.model.reps.KaoQinReps;
import com.yhf.xuedaoqian.model.reps.SignInfoReps;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/12 14:30
 */
@Mapper
public interface SignInInfoDao {

    void insertSignInfo(SignInInfo signInInfo);

    void insertSignInfoList(List<SignInInfo> signInInfoList);

    void updateSignFlag(@Param("signId")String signId,@Param("studentId") String studentId);

    List<SignInfoReps> selectSignInfo(String signId);

    List<KaoQinReps> selectSignSuccessNum(String curriculumId);

    Integer selectSignSuccessNumByStudentId(String studentId);
}
