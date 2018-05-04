package rest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Person entity.
 */
// @Entity annotation indicates that this class is persisted with JPA.
// Default table is PERSON
@Entity
public class Person {
    // @Id indicates that the attribute id is the entity identifier (equivalent to
    // the primary key in the table)
    @Id
    // @Generated value indicates that the value of id is generated on resource
    // insertion (save) rather than manually set
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }
}