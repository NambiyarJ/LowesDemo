package com.demo.DBConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration

public class DBConfig {
	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
		
		MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient("127.0.0.1"),"LowesDemo");
		return mongoTemplate;
		
	}
}
