package rest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * Model POJO.
 * <p>
 * Include validation constraints.
 */
public class Greeting {
    @NotNull(message = "Id should be defined")
    @Positive(message = "Id should be greater than 0")
    private long id;

    @NotBlank(message = "Content should be defined")
    @Size(min = 3, max = 10, message = "Content size should be between 3 and 10")
    private String content;

    public Greeting() {
    }

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(long id) {
	this.id = id;
    }

    public void setContent(String content) {
	this.content = content;
    }

}