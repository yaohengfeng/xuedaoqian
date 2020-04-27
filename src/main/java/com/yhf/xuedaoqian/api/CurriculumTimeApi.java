package com.yhf.xuedaoqian.api;

import com.yhf.xuedaoqian.model.Curriculum;
import com.yhf.xuedaoqian.model.CurriculumTime;

import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/15 21:20
 */
public interface CurriculumTimeApi {

    /**
    * @Description 创建新的上课时间
    * @Param [curriculumTime]
    * @return void
    * @Author yaohengfeng
    * @Date 2020/4/15
    */
    void createNewCurriculumTime(CurriculumTime curriculumTime);


    /**
    * @Description 更改上课上课时间
    * @Param [curriculumTime]
    * @return void
    * @Author yaohengfeng
    * @Date 2020/4/15
    */
    void updateCurriculumTimeInfo(CurriculumTime curriculumTime);

    /**
    * @Description 根据课程id查询上课时间列表
    * @Param [curriculumId]
    * @return java.util.List<com.yhf.xuedaoqian.model.CurriculumTime>
    * @Author yaohengfeng
    * @Date 2020/4/15
    */
    List<CurriculumTime> selectCurriculumTimeList(String curriculumId);

    /**
    * @Description 查询上课时间内容
    * @Param [curriculumTimeId]
    * @return com.yhf.xuedaoqian.model.CurriculumTime
    * @Author yaohengfeng
    * @Date 2020/4/15
    */
    CurriculumTime selectCurriculumTimeInfo(String curriculumTimeId);

    /**
    * @Description 删除上课时间
    * @Param [curriculumTimeId]
    * @return void
    * @Author yaohengfeng
    * @Date 2020/4/15
    */
    void deleteCurriculumTime(String curriculumTimeId);
}
