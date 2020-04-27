package com.yhf.xuedaoqian.dao;

import com.yhf.xuedaoqian.model.Curriculum;
import com.yhf.xuedaoqian.model.reps.CurriculumReps;
import com.yhf.xuedaoqian.model.reps.CurriculumTimeReps;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/13 21:54
 */
@Mapper
public interface CurriculumDao {

    void insertCurriculum(Curriculum curriculum);

    void updateCurriculum(Curriculum curriculum);

    List<Curriculum> selectCurriculumListByClassId(String classId);

    Curriculum selectCurriculumInfo(String curriculumId);

    CurriculumReps selectCurriculumRepsInfo(String curriculumId);

    List<CurriculumTimeReps> selectAllCurriculumTimeRepsByTeacherId(@Param("teacherId") String teacherId,@Param("weekDay")String weekDay);

    void deleteCurriculumByCurriculumId(String curriculumId);
}
