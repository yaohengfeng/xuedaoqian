package com.yhf.xuedaoqian.api;

import com.yhf.xuedaoqian.model.Curriculum;
import com.yhf.xuedaoqian.model.reps.CurriculumReps;
import com.yhf.xuedaoqian.model.reps.CurriculumRequestReps;
import com.yhf.xuedaoqian.model.reps.CurriculumTimeReps;

import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/13 22:17
 */
public interface CurriculumApi {

    /**
     * @return void
     * @Description 创建课程
     * @Param [curriculum]
     * @Author yaohengfeng
     * @Date 2020/4/13
     */
    void createCurriculum(Curriculum curriculum);

    /**
     * @return void
     * @Description 修改课程信息
     * @Param [curriculum]
     * @Author yaohengfeng
     * @Date 2020/4/13
     */
    void updateCurriculum(Curriculum curriculum);

    /**
     * @return void
     * @Description 删除课程
     * @Param [curriculumId]
     * @Author yaohengfeng
     * @Date 2020/4/13
     */
    void deleteCurriculum(String curriculumId);

    /**
     * @return java.util.List<com.yhf.xuedaoqian.model.Curriculum>
     * @Description 查询课程列表
     * @Param [classId]
     * @Author yaohengfeng
     * @Date 2020/4/13
     */
    List<Curriculum> selectCurriculumList(String classId);

    /**
     * @return com.yhf.xuedaoqian.model.Curriculum
     * @Description 查询课程信息
     * @Param [curriculum]
     * @Author yaohengfeng
     * @Date 2020/4/13
     */
    CurriculumReps selectCurriculumInfo(String curriculumId);

    /**
    * @Description 获取周几的课程
    * @Param [curriculumRequestReps]
    * @return java.util.List<com.yhf.xuedaoqian.model.reps.CurriculumTimeReps>
    * @Author yaohengfeng
    * @Date 2020/4/24
    */
    List<CurriculumTimeReps> selectCurriculumTimeByTeacherId(CurriculumRequestReps curriculumRequestReps);
}