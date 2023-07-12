package com.garvitrajput.exittest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExitTestApplication {

	public static void main(String[] args) {
		System.out.println("=====================Running Ecommerce API Backend=====================");
		SpringApplication.run(ExitTestApplication.class, args);
		System.out.println("=====================Backend Running=====================");

	}

}
