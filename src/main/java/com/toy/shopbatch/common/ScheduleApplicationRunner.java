package com.toy.shopbatch.common;

import com.toy.shopbatch.domain.Schedule;
import com.toy.shopbatch.quartz.QuartzUtil;
import com.toy.shopbatch.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduleApplicationRunner implements ApplicationRunner {

    private final ScheduleRepository scheduleRepository;
    private final QuartzUtil quartzUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Schedule> schedules = scheduleRepository.findAll("Y");

        for (Schedule schedule : schedules) {
            quartzUtil.addScheduleJob(String.valueOf(schedule.getId()), schedule.getJobName(), schedule.getCron());
        }
    }
}
