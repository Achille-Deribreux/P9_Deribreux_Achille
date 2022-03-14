package com.mediscreen.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesApplication.class, args);
	}

	//TODO remove this
	/*
	front:
        build: ./front/.
        image: front
        container_name: front
        ports:
            -   3000:3000

	 */

}
