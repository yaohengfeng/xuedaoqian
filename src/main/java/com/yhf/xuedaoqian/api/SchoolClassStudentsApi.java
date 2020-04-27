package com.yhf.xuedaoqian.api;

import com.yhf.xuedaoqian.model.SchoolClassStudents;

import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/19 20:32
 */
public interface SchoolClassStudentsApi {
    /**
     * @return void
     * @Description 加入班级
     * @Param [classStudents]
     * @Author yaohengfeng
     * @Date 2020/3/19
     */
    void addClass(SchoolClassStudents classStudents);

    /**
     * @return void
     * @Description 退出班级
     * @Param [userId]
     * @Author yaohengfeng
     * @Date 2020/3/19
     */
    void exitClass(String userId);

    /**
     * @return java.util.List<com.yhf.xuedaoqian.model.SchoolClassStudents>
     * @Description 查询班级里面所有人员
     * @Param [classId]
     * @Author yaohengfeng
     * @Date 2020/3/19
     */
    List<SchoolClassStudents> selectClassStudents(String classId);

    /**
    * @Description 查询是否已经加入班级
    * @Param [classStudents]
    * @return java.lang.Boolean
    * @Author yaohengfeng
    * @Date 2020/4/18
    */
    Boolean checkOnClass(SchoolClassStudents classStudents);
}
