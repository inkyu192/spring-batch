package spring.batch.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBatchKotlinApplication

fun main(args: Array<String>) {
	runApplication<SpringBatchKotlinApplication>(*args)
}
