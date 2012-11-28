package com.jayway.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Path("/simple")
@Component
public class SimpleResource {
	@GET
	public Response get(@QueryParam("param") String msg) {
		return Response.ok("param was: " + msg).build();
	}

	@GET
	@Path("secret")
    @PreAuthorize("hasRole('secret')")
	public String secret() {
		return "this is secret";
	}
}
