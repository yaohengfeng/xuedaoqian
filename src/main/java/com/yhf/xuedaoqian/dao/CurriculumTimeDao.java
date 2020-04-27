package com.yhf.xuedaoqian.dao;

import com.yhf.xuedaoqian.model.CurriculumTime;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/15 8:51
 */
@Mapper
public interface CurriculumTimeDao {


    void insertNewCurriculumTime(CurriculumTime curriculumTime);

    void updateCurriculumTimeInfo(CurriculumTime curriculumTime);

    List<CurriculumTime> selectCurriculumTimeList(String curriculumId);

    CurriculumTime selectCurriculumTimeInfo(String curriculumTimeId);

    void deleteCurriculumTime(String curriculumTimeId);

    void deleteCurriculumTimeByCurriculumId(String curriculumId);
}
