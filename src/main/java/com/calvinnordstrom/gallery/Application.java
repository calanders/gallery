package com.calvinnordstrom.gallery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The {@link Application} class is the starting point of the Java Spring
 * server. When the main method of this class is executed, the Java Spring
 * server will run.
 */
@SpringBootApplication
public class Application {
	/**
	 * The {@link Logger} used around this Java application.
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	/**
	 * The entry point of this Java application.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
