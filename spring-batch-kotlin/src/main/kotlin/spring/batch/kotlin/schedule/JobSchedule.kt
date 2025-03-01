package spring.batch.kotlin.schedule

import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.*

@Component
class JobSchedule(
    private val jobLauncher: JobLauncher,
    private val logJob: Job
) {
    private val log = LoggerFactory.getLogger(JobSchedule::class.java)

    // @Scheduled(cron = "*/5 * * * * *")
    fun runLogJob() {
        val jobParameters = JobParametersBuilder()
            .addString("targetDate", LocalDate.now().toString())
            .addString("uuid", UUID.randomUUID().toString())
            .toJobParameters()

        runCatching { jobLauncher.run(logJob, jobParameters) }
            .onFailure {
                log.error("Error the job", it)
                throw RuntimeException(it)
            }
    }
}