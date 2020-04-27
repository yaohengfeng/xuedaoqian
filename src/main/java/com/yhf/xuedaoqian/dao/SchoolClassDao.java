package com.yhf.xuedaoqian.dao;

import com.yhf.xuedaoqian.model.SchoolClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/15 14:35
 */
@Mapper
public interface SchoolClassDao {
    void insertClass(SchoolClass schoolClass);

    void updateClassByClassId(SchoolClass schoolClass);

    SchoolClass selectClassInfo(String classId);

    List<SchoolClass> selectClassByTeacherId(String teacherId);

    List<SchoolClass> selectClassByStudentId(String studentId);

    List<SchoolClass> selectClassList();

    List<SchoolClass> selectClassInfoByName(String className);

    void deleteClass(String classId);
}
