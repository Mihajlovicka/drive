package com.example.driveBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DriveBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriveBackApplication.class, args);
	}

}
