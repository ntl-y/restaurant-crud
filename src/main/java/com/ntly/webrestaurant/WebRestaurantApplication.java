package com.ntly.webrestaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.ntly.webrestaurant.controllers"})
 public class WebRestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebRestaurantApplication.class, args);
	}

}
