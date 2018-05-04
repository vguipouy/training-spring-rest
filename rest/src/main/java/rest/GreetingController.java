package rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Greeting resource.
 * 
 * @see Greeting
 */
// RestController combines @Controller and @ResponseBody annotation to serialze
// or deserialize object to and from JSON
@RestController
// Request mapping on controller level define the base url for all the
// controller method.
// In a RESTful controller, this url defines the handled resource
@RequestMapping("/greeting")
public class GreetingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);
    // Map storage mocking a database or a service handling storage
    private Map<Integer, Greeting> greetingsStorage = new HashMap<>();

    /**
     * list method : GET on base url return the collection of all resources
     * 
     * @return Collection of existing resource
     */
    @GetMapping
    public Collection<Greeting> list() {
	return greetingsStorage.values();
    }

    /**
     * get / fetch method : GET on base url/{id} will return a specific resource.
     * Will return a 404 not found if resource with identifier dos not exist. <br/>
     * PathVariable annotation maps {id} in url to the id method parameter
     * 
     * @param id
     *            Resource identifier
     * @return Found resource
     */
    @GetMapping("/{id}")
    public ResponseEntity<Greeting> get(@PathVariable("id") Integer id) {
	LOGGER.info("Fetching resource with identifier: " + id.toString());

	Greeting greeting = greetingsStorage.get(id);
	if (greeting != null) {
	    return ResponseEntity.ok(greeting);
	} else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
    }

    /**
     * Create a new resource. <br/>
     * Resource to create is passed in the request body. <br/>
     * Created resource is returned. 409 Conflict error will be returned if the
     * resource already exist. RequestBody annotation indicate that request body
     * (JSON) as to be deserialized to the greetingTocreate parameter<br/>
     * Valid annotation indicates that a validation is performed using
     * javax.validation annotations set on Greeting class
     * 
     * @param greetingTocreate
     *            Resource to create
     * @return Created resource
     */
    @PostMapping
    public ResponseEntity<Greeting> create(@Valid @RequestBody Greeting greetingTocreate) {
	int id = (int) greetingTocreate.getId();
	LOGGER.info("Creating new resource: " + id);

	// Create error if id already exists
	if (greetingsStorage.get(id) != null) {
	    LOGGER.info("Resource with id {} already exists", id);
	    return new ResponseEntity<Greeting>(HttpStatus.CONFLICT);
	}

	greetingsStorage.put(id, greetingTocreate);
	Greeting createdGreeting = greetingsStorage.get(id);

	return ResponseEntity.ok(createdGreeting);
    }

    /**
     * Update an existing resource.<br/>
     * Resource to update is passed in the request body. <br/>
     * 404 not found error is returned if the resource to update does not exist.
     * 
     * @param greetingToUpdate
     *            Resource to update
     * @return updated resource
     */
    @PatchMapping
    public ResponseEntity<Greeting> update(@Valid @RequestBody Greeting greetingToUpdate) {
	int id = (int) greetingToUpdate.getId();
	Greeting existingGreeting = greetingsStorage.get(id);

	// Create error if resource to update does not exist already exists
	if (existingGreeting == null) {
	    return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
	}

	existingGreeting.setContent(greetingToUpdate.getContent());
	greetingsStorage.put(id, existingGreeting);

	return ResponseEntity.ok(existingGreeting);
    }

    /**
     * Delete a resource identified by the provided id.<br/>
     * If resource does not exist, return a 404 Not found error.
     * 
     * @param id
     *            Resource identifier
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Greeting> delete(@PathVariable int id) {
	Greeting existingGreeting = greetingsStorage.get(id);

	if (existingGreeting == null) {
	    return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
	}

	greetingsStorage.remove(id);

	return new ResponseEntity(HttpStatus.OK);
    }
}
