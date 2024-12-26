package spring.batch.java.validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.util.StringUtils;

public class TargetDateValidator implements JobParametersValidator {

	private static final String PARAMETER_NAME = "targetDate";

	@Override
	public void validate(JobParameters parameters) throws JobParametersInvalidException {
		String targetDate = parameters.getString(PARAMETER_NAME);

		if (!StringUtils.hasText(targetDate)) {
			throw new JobParametersInvalidException("%s 가 빈 문자열이거나 존재하지 않습니다.".formatted(PARAMETER_NAME));
		}

		try {
			LocalDate.parse(targetDate);
		} catch (DateTimeParseException e) {
			throw new JobParametersInvalidException("%s 가 날짜 형식의 문자열이 아닙니다.".formatted(PARAMETER_NAME));
		}
	}
}
