package com.toy.shopbatch.common;

import com.toy.shopbatch.domain.Schedule;
import com.toy.shopbatch.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduleApplicationRunner implements ApplicationRunner {

    private final Scheduler scheduler;
    private final ScheduleRepository scheduleRepository;
    private final ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Schedule> schedules = scheduleRepository.findAll();

        for (Schedule schedule : schedules) {
            String jobName = schedule.getJobName();
            QuartzJobBean bean = (QuartzJobBean) applicationContext.getBean(Class.forName(jobName));

            JobDetail jobDetail = JobBuilder
                    .newJob(bean.getClass())
                    .withIdentity(jobName)
                    .build();

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(String.valueOf(schedule.getId()))
                    .withSchedule(CronScheduleBuilder.cronSchedule(schedule.getCron()))
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
        }
    }
}
