package com.jayway.template.sample;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Path("/sample")
@Component
public class SampleResource {
	
	@GET
	public String get() {
		return "This is public";
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("developers")
	public List<PersonDTO> developers() {
		return Arrays.asList(new PersonDTO("Jan", "Kronquist"), new PersonDTO("Oskar", "Wickström"));
	}

	@GET
	@Path("greet")
    @PreAuthorize("isAuthenticated()")
	public String yourEyesOnly() {
		return "Hello world";
	}

	@GET
	@Path("secret")
    @PreAuthorize("hasRole('secret')")
	public String secret() {
		return "this is secret";
	}
}
