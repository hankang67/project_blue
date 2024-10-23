package com.sparta.projectblue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableJpaAuditing
@SpringBootApplication
public class ProjectBlueApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectBlueApplication.class, args);
	}

}
