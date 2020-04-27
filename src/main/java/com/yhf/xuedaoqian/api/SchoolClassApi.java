package com.yhf.xuedaoqian.api;

import com.yhf.xuedaoqian.model.SchoolClass;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/16 21:35
 */
public interface SchoolClassApi {
    /**
     * @return void
     * @Description 创建新的班级
     * @Param [schoolClass]
     * @Author yaohengfeng
     * @Date 2020/3/16
     */
    void createNewClass(SchoolClass schoolClass);

    /**
     * @return void
     * @Description 修改班级名称
     * @Param [className]
     * @Author yaohengfeng
     * @Date 2020/3/16
     */
    void updateClassName(SchoolClass schoolClass);

    /**
     * @return SchoolClass
     * @Description 查询班级信息
     * @Param [classId]
     * @Author yaohengfeng
     * @Date 2020/3/16
     */
    SchoolClass selectClassInfo(String classId);

    /**
     * @return java.util.List<com.yhf.xuedaoqian.model.SchoolClass>
     * @Description 查询我创建的班级
     * @Param [teacherId]
     * @Author yaohengfeng
     * @Date 2020/3/16
     */
    List<SchoolClass> selectClassByTeacherId(String teacherId);

    /**
     * @return java.util.List<com.yhf.xuedaoqian.model.SchoolClass>
     * @Description 查询我加入的班级
     * @Param [studentId]
     * @Author yaohengfeng
     * @Date 2020/3/16
     */
    List<SchoolClass> selectClassByStudentId(String studentId);

    /**
    * @Description 删除班级
    * @Param [classId]
    * @return void
    * @Author yaohengfeng
    * @Date 2020/4/14
    */
    void deleteClass(String classId);
    
    /**
    * @Description 查询所有的班级
    * @Param []
    * @return java.util.List<com.yhf.xuedaoqian.model.SchoolClass>
    * @Author yaohengfeng
    * @Date 2020/4/18
    */
    List<SchoolClass> selectAllClass();
    
    /**
    * @Description 根据班级名字关键字查询
    * @Param [className]
    * @return java.util.List<com.yhf.xuedaoqian.model.SchoolClass>
    * @Author yaohengfeng
    * @Date 2020/4/18
    */
    List<SchoolClass> selectClassByName(String className);
    
}
