package com.toy.shopbatch.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduleApplicationRunner implements ApplicationRunner {

    private final Scheduler scheduler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobDetail jobDetail = JobBuilder
                .newJob(TestJob.class)
                .withIdentity("LogJob")
                .build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("111")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/1 * * * * ?"))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }
}
