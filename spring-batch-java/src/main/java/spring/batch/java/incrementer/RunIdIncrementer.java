package spring.batch.java.incrementer;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

public class RunIdIncrementer implements JobParametersIncrementer {

	private static final String RUN_ID = "run.id";

	@Override
	public JobParameters getNext(JobParameters parameters) {
		return new JobParametersBuilder()
			.addLong(RUN_ID, getRunId(parameters))
			.toJobParameters();
	}

	private Long getRunId(JobParameters parameters) {
		long runId = 0L;
		if (parameters != null && !parameters.isEmpty()) {
			runId = parameters.getLong(RUN_ID);
		}
		return runId + 1;
	}
}
