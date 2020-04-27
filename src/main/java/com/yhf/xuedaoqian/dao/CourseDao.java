package com.yhf.xuedaoqian.dao;

import com.yhf.xuedaoqian.model.Course;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/11 14:23
 */
@Mapper
public interface CourseDao {

    void insertCourseInfo(Course course);

    void updateCourseInfo(Course course);

    void selectCourseInfoByClassId(String classId);

    void deleteCourseById(String courseId);

}
