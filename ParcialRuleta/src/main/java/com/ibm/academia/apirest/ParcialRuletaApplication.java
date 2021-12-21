package com.ibm.academia.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ParcialRuletaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParcialRuletaApplication.class, args);
	}

}
