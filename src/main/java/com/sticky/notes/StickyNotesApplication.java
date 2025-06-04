package com.sticky.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.sticky.notes.controllers, com.sticky.notes.models, com.sticky.notes.repositories, com.sticky.notes.services")
public class StickyNotesApplication {
	public static void main(String[] args) {
		SpringApplication.run(StickyNotesApplication.class,args);
	}
}