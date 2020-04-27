package com.yhf.xuedaoqian.dao;

import com.yhf.xuedaoqian.model.Sign;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/3/20 20:42
 */
@Mapper
public interface SignDao {

    void insertSign(Sign sign);

    void updateSingFlag(Sign sign);

    List<Sign> selectSignByCurriculumId(String curriculumId);

    Sign selectSignBySignId(String signId);

    String selectSignIdByCurriculumId(String curriculumId);
}
