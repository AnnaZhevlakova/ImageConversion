package com.example.ImageConversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class ImageConversionApplication {

	public static void main(String[] args) {

		SpringApplication.run(ImageConversionApplication.class, args);
		System.out.println("Swagger UI доступен по:");
		System.out.println("http://localhost:8080/swagger-ui.html");
	}

}
