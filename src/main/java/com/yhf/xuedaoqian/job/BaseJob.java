package com.yhf.xuedaoqian.job;

import com.yhf.xuedaoqian.job.annotation.BizScheduler;
import org.quartz.Job;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

/**
 * @author yaohengfeng
 * @version 1.0
 * @date 2020/5/22 1:06
 */
@BizScheduler
public abstract class BaseJob implements Job {

}
