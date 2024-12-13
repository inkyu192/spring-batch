package spring.batch.java.validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.util.StringUtils;

public class LocalDateParameterValidator implements JobParametersValidator {

	@Override
	public void validate(JobParameters parameters) throws JobParametersInvalidException {
		String targetDate = parameters.getString("targetDate");

		if (!StringUtils.hasText(targetDate)) {
			throw new JobParametersInvalidException("targetDate is required");
		}

		try {
			LocalDate.parse(targetDate);
		} catch (DateTimeParseException e) {
			throw new JobParametersInvalidException("targetDate is not a valid date");
		}
	}
}
