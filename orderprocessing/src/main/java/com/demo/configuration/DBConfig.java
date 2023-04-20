package com.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;


@Configuration

public class DBConfig {
	
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	MongoTemplate mongoTemplate() throws Exception {
		
		return mongoTemplate;
		
	}
}
