package spring.batch.java.schedule;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobSchedule {

	private final JobLauncher jobLauncher;
	private final Job logJob;

	// @Scheduled(cron = "*/5 * * * * *")
	public void runLogJob() {
		JobParameters jobParameters = new JobParametersBuilder()
			.addString("targetDate", String.valueOf(LocalDate.now()))
			.addString("uuid", UUID.randomUUID().toString())
			.toJobParameters();

		try {
			jobLauncher.run(logJob, jobParameters);
		} catch (Exception e) {
			log.error("Error the job", e);
			throw new RuntimeException(e);
		}
	}
}
