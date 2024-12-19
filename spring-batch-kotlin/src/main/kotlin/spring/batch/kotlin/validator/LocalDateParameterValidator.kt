package spring.batch.kotlin.validator

import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersInvalidException
import org.springframework.batch.core.JobParametersValidator
import org.springframework.util.StringUtils
import java.time.LocalDate
import java.time.format.DateTimeParseException

class LocalDateParameterValidator : JobParametersValidator {

    override fun validate(parameters: JobParameters?) {
        val targetDate = parameters?.getString("targetDate")

        if (!StringUtils.hasText(targetDate)) {
            throw JobParametersInvalidException("targetDate is required")
        }

        try {
            LocalDate.parse(targetDate!!)
        } catch (e: DateTimeParseException) {
            throw JobParametersInvalidException("targetDate is not a valid date")
        }
    }
}