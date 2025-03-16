package com.emtech.students_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories("com.emtech.students_microservice.repository")
public class StudentsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentsMicroserviceApplication.class, args);
	}

}
