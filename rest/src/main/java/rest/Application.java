package rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point.
 * <p>
 * Executable java class launching Spring boot application.<br/>
 * Start the embedded application server (here, Tomcat).<br/>
 * Create application context and instantiate the spring beans.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }
}
