package cn.xiaoneng.study.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @author zhaoht
 * @date 2016/3/31 14:27
 */
public class SchedulerTest {
    public static void main(String[] args) {

    SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try{
            scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail = new JobDetail("job1","jgroup1",MyJob.class);
            SimpleTrigger simpleTrigger = new SimpleTrigger("simpleTrigger","triggerGroup");

            simpleTrigger.setStartTime(new Date());

            simpleTrigger.setRepeatInterval(1000);
            simpleTrigger.setRepeatCount(120000000);

            scheduler.scheduleJob(jobDetail,simpleTrigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
