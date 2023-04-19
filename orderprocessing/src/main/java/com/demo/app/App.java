package com.demo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories("com.demo.repository")
@ComponentScan("com.demo.*")
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
