package rest;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository enabling access to data storage using Spring Data.<br/>
 * RepositoryRestResource annotation indicates that the repository is
 * automatically exposed with a REST service. PagingAndSortingRepository
 */
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
    // Implementation and request will be automatically generateg by Spring data so
    // as to fetch the person using its last name.
    List<Person> findByLastName(String name);
}