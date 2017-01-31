package com.boris.reservations;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.boris.reservations.TestConfing.BeanConfig;
import com.boris.reservations.TestConfing.JacksonConfig;
import com.boris.reservations.service.VenueService;
import com.boris.reservations.service.VenueServiceStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootApplication
@Import({ JacksonConfig.class, BeanConfig.class })
public class TestConfing extends WebMvcConfigurerAdapter {

	@Configuration
	public class JacksonConfig {

		@Bean
		@Primary
		public ObjectMapper serializingObjectMapper() {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new Jdk8Module());
			objectMapper.registerModule(new JavaTimeModule());
			return objectMapper;
		}
	}

	@Configuration
	public class BeanConfig {
		@Bean
		public VenueService venueService() {
			return new VenueServiceStub();
		}
	}
}
