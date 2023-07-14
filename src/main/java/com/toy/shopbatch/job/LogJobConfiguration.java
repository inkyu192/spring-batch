package com.toy.shopbatch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class LogJobConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public Job logJob(Step logJobStep1) {
        return new JobBuilder("logJob", jobRepository)
                .start(logJobStep1)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step logJobStep1(Tasklet logTasklet) {
        return new StepBuilder("logJobStep1", jobRepository)
                .tasklet(logTasklet, platformTransactionManager)
                .build();
    }
}
