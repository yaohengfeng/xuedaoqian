package com.yhf.xuedaoqian.job;

import com.yhf.xuedaoqian.common.spring.ApplicationContextFactory;
import com.yhf.xuedaoqian.dao.SignDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/5/22 1:26
 */
@Component
@Slf4j
public class SignDataBaseJob {
    @Scheduled(cron = "0 0 2 * * ?")
    public void endSignIn() {
        System.out.println("测试");
        log.info("定时任务执行SignDataBaseJob，结束数据库中一天没有结束的所有签到");
        SignDao signDao = ApplicationContextFactory.getBean(SignDao.class);
        if (signDao == null) {
            log.info("signDao没有从容器中获取到");
            return;
        }
        signDao.autoUpdateSingFlag();
    }

}
