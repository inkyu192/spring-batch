package com.toy.shopbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ShopBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopBatchApplication.class, args);
	}

}
