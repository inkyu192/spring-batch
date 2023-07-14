package com.toy.shopbatch.quartz;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.quartz.JobExecutionContext;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class MemberQuartz extends QuartzJobBean {

    private final Job memberJob;
    private final JobLauncher jobLauncher;

    @SneakyThrows
    @Override
    protected void executeInternal(JobExecutionContext context) {
        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date())
                .toJobParameters();

        jobLauncher.run(memberJob, jobParameters);
    }
}
