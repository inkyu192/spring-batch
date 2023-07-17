package com.toy.shopbatch.quartz;

import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuartzUtil {

    private final Scheduler scheduler;
    private final ApplicationContext applicationContext;

    public void addScheduleJob(String id, String jobName, String cron) {
        try {
            QuartzJobBean bean = (QuartzJobBean) applicationContext.getBean(Class.forName(jobName));

            JobDetail jobDetail = JobBuilder
                    .newJob(bean.getClass())
                    .withIdentity(jobName)
                    .build();

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(new TriggerKey(id))
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateScheduleJob(String id, String cron) {
        try {
            TriggerKey triggerKey = new TriggerKey(id);

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .build();

            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteScheduleJob(String id) {
        try {
            scheduler.unscheduleJob(new TriggerKey(id));
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}
