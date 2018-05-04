package com.nelsonmatias.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;



@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.nelsonmatias")
@PropertySources({
	@PropertySource("classpath:application.properties"),
	@PropertySource("classpath:auth0.properties")})
public class Auht0Application {

	public static void main(String[] args) {
		System.out.println("ajksndkjasndkjansdkjsank");
		SpringApplication.run(Auht0Application.class, args);
	}
}
