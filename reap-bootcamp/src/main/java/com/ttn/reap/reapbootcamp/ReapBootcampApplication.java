package com.ttn.reap.reapbootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ttn.reap.reapbootcamp.repository")
@EntityScan(basePackages = {"com.ttn.reap.reapbootcamp.entity"})
public class ReapBootcampApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReapBootcampApplication.class, args);
	}
}
