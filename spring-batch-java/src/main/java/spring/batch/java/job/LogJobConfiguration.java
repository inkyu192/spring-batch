package spring.batch.java.job;

import java.time.LocalDate;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spring.batch.java.incrementer.RunIdIncrementer;
import spring.batch.java.validator.TargetDateValidator;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class LogJobConfiguration {

	private final JobRepository jobRepository;
	private final PlatformTransactionManager platformTransactionManager;

	@Bean
	public Job logJob(Step logJobStep1) {
		return new JobBuilder("logJob", jobRepository)
			.incrementer(new RunIdIncrementer()) // jobLauncher 를 사용해 직접 호출하면 incrementer 는 적용되지 않음
			.validator(new TargetDateValidator())
			.start(logJobStep1)
			.build();
	}

	@Bean
	@JobScope
	public Step logJobStep1(@Value("#{jobParameters['targetDate']}") LocalDate targetDate, Tasklet logTasklet) {
		log.info(String.valueOf(targetDate));
		return new StepBuilder("logJobStep1", jobRepository)
			.tasklet(logTasklet, platformTransactionManager)
			.build();
	}
}
