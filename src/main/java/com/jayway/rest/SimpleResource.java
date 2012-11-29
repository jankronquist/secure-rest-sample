package com.jayway.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Path("/simple")
@Component
public class SimpleResource {
	@GET
	public String get() {
		return "This is public";
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
