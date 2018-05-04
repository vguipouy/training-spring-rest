package rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * REST client consuming the services.
 * <p>
 * This class is an executable class. <br/>
 * On real life projects, the client would be on a separate project.
 */
public class ClientApplication {
    // slf4j logger printing information messages
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientApplication.class);

    public static void main(String[] args) {
	// Instantiate the RestTemplate
	// The RestTemplate call the remote REST web services and handles
	// JSON serialization of parameters (from object to JSON),
	// JSON deserialization of service result (from JSON to object)
	// Generate exceptions on error (return status <> 200)
	RestTemplate restTemplate = new RestTemplate();

	// Create a resource with a POST
	Greeting greeting = new Greeting(19, "rest");
	LOGGER.info("Creating new greeting");
	Greeting createdGreeting =
		restTemplate.postForObject("http://localhost:8080/greeting", greeting, Greeting.class);
	LOGGER.info("Created greeting: " + createdGreeting.getContent());

	// Get a specific resource identified by its id
	LOGGER.info("Fetching greeting by id");
	Greeting fetchedGreeting =
		restTemplate.getForObject("http://localhost:8080/greeting/{id}", Greeting.class, 19);
	LOGGER.info("Found greeting " + fetchedGreeting.getContent());

	// Get collection of resources as array
	LOGGER.info("Fetching all greetings");
	Greeting[] greetings = restTemplate.getForObject("http://localhost:8080/greeting", Greeting[].class);
	for (Greeting greet : greetings) {
	    LOGGER.info("Found: " + greet.getContent());
	}

	// Delete a specific resource identified by its id
	restTemplate.delete("http://localhost:8080/greeting/{id}", 19);
	LOGGER.info("Resource deleted ");
    }
}
