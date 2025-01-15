package spring.batch.kotlin.job

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import spring.batch.kotlin.incrementer.RunIdIncrementer
import spring.batch.kotlin.validator.TargetDateValidator

@Configuration
class LogJobConfiguration(
    private val jobRepository: JobRepository,
    private val platformTransactionManager: PlatformTransactionManager
) {
    @Bean
    fun logJob(logJobStep1: Step): Job =
        JobBuilder("logJob", jobRepository)
            .incrementer(RunIdIncrementer()) // jobLauncher 를 사용해 직접 호출하면 incrementer 는 적용되지 않음
            .validator(TargetDateValidator())
            .start(logJobStep1)
            .build()

    @Bean
    fun logJobStep1(logTasklet: Tasklet): Step =
        StepBuilder("logJobStep1", jobRepository)
            .tasklet(logTasklet, platformTransactionManager)
            .build()
}