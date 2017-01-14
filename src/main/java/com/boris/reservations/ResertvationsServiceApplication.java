package com.boris.reservations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.boris.reservations.config.MongoConfig;

@SpringBootApplication
@Import(MongoConfig.class)
public class ResertvationsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResertvationsServiceApplication.class, args);
	}
}
