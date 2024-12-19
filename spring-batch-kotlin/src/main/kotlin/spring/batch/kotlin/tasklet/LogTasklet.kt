package spring.batch.kotlin.tasklet

import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
class LogTasklet : Tasklet {

    private val log = LoggerFactory.getLogger(LogTasklet::class.java)

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        log.info("==========================")
        log.info("logJobStep1");
        log.info("==========================")
        return RepeatStatus.FINISHED
    }
}