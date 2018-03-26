package com.app;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.dto.UserDTO;
import com.app.service.UserService;

@SpringBootApplication
@ComponentScan(value = "com.app")
@EntityScan("com.app.entity")
@EnableJpaRepositories("com.app.repo")
@Controller
public class WebApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserService userService) {
		return (evt) -> Arrays.asList("ram,john,venu".split(",")).forEach(user -> {
			UserDTO userDto = new UserDTO(user, "password", "user@gmail.com", "SIPCOT,CHENNAI,INDIA,603103");
			userService.createUser(userDto);
		});
	}

	@RequestMapping(value = "")
	public String home() {
		return "index.html";
	}

}
