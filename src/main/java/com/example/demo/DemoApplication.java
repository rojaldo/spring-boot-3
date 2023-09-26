package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.demo.trivial.TrivialCardDto;
import com.example.demo.trivial.TrivialService;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CommandLineRunner addBeers(TrivialService trivialService) {
		return (args) -> {
			trivialService.getCardsFromApi("https://opentdb.com/api.php?amount=20");
			
		};
	}


}
