package com.boris.reservations;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.boris.reservations.TestConfing.JacksonConfig;
import com.boris.reservations.TestConfing.MongoTestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@SpringBootApplication
@Import({MongoTestConfig.class, JacksonConfig.class})
public class TestConfing extends WebMvcConfigurerAdapter {
     
	@Configuration
	class MongoTestConfig extends AbstractMongoConfiguration {

		@Override
		protected String getDatabaseName() {
			return "test";
		}

		@Override
		public Mongo mongo() throws Exception {
			return new MongoClient("127.0.0.1", 27017);
		}

		@Override
		protected String getMappingBasePackage() {
			return "com.boris.reservations.dao";
		}
	}
	
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
}
