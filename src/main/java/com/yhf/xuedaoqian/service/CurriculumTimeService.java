package com.yhf.xuedaoqian.service;

import com.yhf.xuedaoqian.api.CurriculumTimeApi;
import com.yhf.xuedaoqian.dao.CurriculumDao;
import com.yhf.xuedaoqian.dao.CurriculumTimeDao;
import com.yhf.xuedaoqian.model.Curriculum;
import com.yhf.xuedaoqian.model.CurriculumTime;
import com.yhf.xuedaoqian.util.ToolUtil;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/4/15 21:33
 */
@Service
public class CurriculumTimeService implements CurriculumTimeApi {

    private CurriculumDao curriculumDao;

    private CurriculumTimeDao curriculumTimeDao;

    @Autowired
    public CurriculumTimeService(CurriculumDao curriculumDao,
                                 CurriculumTimeDao curriculumTimeDao) {
        this.curriculumDao = curriculumDao;
        this.curriculumTimeDao = curriculumTimeDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createNewCurriculumTime(CurriculumTime curriculumTime) {
        Assert.notNull(curriculumTime.getCurriculumId(),"课程id不能为空");
        Assert.notNull(curriculumTime.getWeekDay(),"weekDay不能为空");
        Assert.notNull(curriculumTime.getStartTime(),"上课时间不能为空");
        Assert.notNull(curriculumTime.getEndTime(),"下课时间不能为空");
        Curriculum curriculum = curriculumDao.selectCurriculumInfo(curriculumTime.getCurriculumId());
        if (curriculum==null){
            throw new RuntimeException("请输入正确的课程id");
        }
        curriculumTime.setCurriculumTimeId(ToolUtil.getUUid());
        curriculumTime.setCreateAt(new Date());
        curriculumTime.setUpdateAt(new Date());
        curriculumTimeDao.insertNewCurriculumTime(curriculumTime);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCurriculumTimeInfo(CurriculumTime curriculumTime) {
        Assert.notNull(curriculumTime.getCurriculumTimeId(),"课程时间表id不能为空");
        CurriculumTime curriculumTimeDb = curriculumTimeDao.selectCurriculumTimeInfo(curriculumTime.getCurriculumTimeId());
        if (curriculumTimeDb==null){
            throw new RuntimeException("课程时间表id错误");
        }
        System.out.println(curriculumTime);
        curriculumTime.setUpdateAt(new Date());
        curriculumTimeDao.updateCurriculumTimeInfo(curriculumTime);
    }

    @Override
    public List<CurriculumTime> selectCurriculumTimeList(String curriculumId) {
        Assert.notNull(curriculumId,"课程id不能为空");
        Curriculum curriculum = curriculumDao.selectCurriculumInfo(curriculumId);
        if (curriculum==null){
            throw new RuntimeException("请输入正确的课程id");
        }
        return curriculumTimeDao.selectCurriculumTimeList(curriculumId);
    }

    @Override
    public CurriculumTime selectCurriculumTimeInfo(String curriculumTimeId) {
        Assert.notNull(curriculumTimeId,"课程时间表id不能为空");
        CurriculumTime curriculumTime = curriculumTimeDao.selectCurriculumTimeInfo(curriculumTimeId);
        if (curriculumTime==null){
            throw new RuntimeException("课程时间表id错误");
        }
        return curriculumTime;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCurriculumTime(String curriculumTimeId) {
        Assert.notNull(curriculumTimeId,"课程时间表id不能为空");
        CurriculumTime curriculumTime = curriculumTimeDao.selectCurriculumTimeInfo(curriculumTimeId);
        if (curriculumTime==null){
            throw new RuntimeException("课程时间表id错误");
        }
        curriculumTimeDao.deleteCurriculumTime(curriculumTimeId);
    }
}
