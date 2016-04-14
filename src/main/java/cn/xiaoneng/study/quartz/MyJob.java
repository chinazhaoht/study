package cn.xiaoneng.study.quartz;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author zhaoht
 * @date 2016/3/31 14:25
 */
public class MyJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("test quartz " + new Date());
    }
}
