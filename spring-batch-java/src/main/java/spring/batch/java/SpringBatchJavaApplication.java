package spring.batch.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringBatchJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchJavaApplication.class, args);
	}

}
