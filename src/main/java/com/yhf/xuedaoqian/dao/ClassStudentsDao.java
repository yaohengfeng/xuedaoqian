package com.yhf.xuedaoqian.dao;

import com.yhf.xuedaoqian.model.SchoolClassStudents;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/15 12:16
 */
@Mapper
public interface ClassStudentsDao {

    List<SchoolClassStudents> selectUserByClassId(String classId);

    void deleteClassUserByUserId(String classStudentId);

    void insertClassStudent(SchoolClassStudents schoolClassStudents);

    void updateClassStudentSignInFlag(String classStudentId);

    void updateSignInFlagByClassIdAndStudentId(@Param("classId") String classId,@Param("studentId") String studentId);

    String selectScIdByClassIdAndStudentId(@Param("classId") String classId,@Param("studentId") String studentId);

    void updateAllStudentSignInFlag(String classId);

    SchoolClassStudents selectUserByScsId(String classStudentId);
}
