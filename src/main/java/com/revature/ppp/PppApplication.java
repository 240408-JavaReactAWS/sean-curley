/*
Main application class for the project.
This is a simple REST backend API for CRUD operations on the Item table in a local database.
 */

package com.revature.ppp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.revature") //Scan com.revature package for Spring Beans
@EntityScan("com.revature.models") //Scan models package for Entities
@EnableJpaRepositories("com.revature.daos")
public class PppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PppApplication.class, args);

	}
}
