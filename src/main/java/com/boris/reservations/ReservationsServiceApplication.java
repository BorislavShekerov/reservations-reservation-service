package com.boris.reservations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.boris.reservations.config.MongoConfig;

@SpringBootApplication
@Import(MongoConfig.class)
public class ReservationsServiceApplication extends WebMvcConfigurerAdapter {


	public static void main(String[] args) {
		SpringApplication.run(ReservationsServiceApplication.class, args);
	}

}
