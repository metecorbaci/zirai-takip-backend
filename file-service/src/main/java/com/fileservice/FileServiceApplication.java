package com.fileservice;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@EntityScan("com.fileservice.model")
@SpringBootApplication
public class FileServiceApplication  {
	public static void main(String[] args) {
		SpringApplication.run(FileServiceApplication.class, args);
	}
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		return new MultipartConfigElement("");
	}
}
