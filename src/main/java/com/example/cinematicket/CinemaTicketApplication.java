package com.example.cinematicket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CinemaTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaTicketApplication.class, args);
	}

}
