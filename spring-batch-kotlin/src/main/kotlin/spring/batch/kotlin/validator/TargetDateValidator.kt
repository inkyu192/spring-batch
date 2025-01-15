package spring.batch.kotlin.validator

import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersInvalidException
import org.springframework.batch.core.JobParametersValidator
import java.time.LocalDate

class TargetDateValidator : JobParametersValidator {

    companion object {
        private const val PARAMETER_NAME = "targetDate"
    }

    override fun validate(parameters: JobParameters?) {
        val targetDate = parameters?.getString(PARAMETER_NAME).orEmpty()

        if (targetDate.isBlank()) {
            throw JobParametersInvalidException("$PARAMETER_NAME 가 빈 문자열이거나 존재하지 않습니다.")
        }

        runCatching { LocalDate.parse(targetDate) }
            .onFailure { throw JobParametersInvalidException("$PARAMETER_NAME 가 날짜 형식의 문자열이 아닙니다.") }
    }
}