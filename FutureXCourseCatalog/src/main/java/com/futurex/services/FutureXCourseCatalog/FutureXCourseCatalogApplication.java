package com.futurex.services.FutureXCourseCatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FutureXCourseCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutureXCourseCatalogApplication.class, args);
	}

}
