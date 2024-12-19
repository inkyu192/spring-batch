package spring.batch.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class SpringBatchKotlinApplication

fun main(args: Array<String>) {
	runApplication<SpringBatchKotlinApplication>(*args)
}
