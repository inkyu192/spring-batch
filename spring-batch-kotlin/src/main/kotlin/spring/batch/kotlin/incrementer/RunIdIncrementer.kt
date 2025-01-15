package spring.batch.kotlin.incrementer

import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.JobParametersIncrementer

class RunIdIncrementer : JobParametersIncrementer {

    companion object {
        private const val RUN_ID = "run.id"
    }

    override fun getNext(parameters: JobParameters?): JobParameters {
        return JobParametersBuilder()
            .addLong(RUN_ID, (parameters?.getLong(RUN_ID) ?: 0L) + 1)
            .toJobParameters()
    }
}