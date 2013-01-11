package com.jayway.template.security;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Path("/authentication")
@Component
public class AuthenticationResource {
	
	@GET
	@Path("google")
	public InputStream google() throws IOException {
		return new ClassPathResource("forceGoogleLogin.html").getInputStream();
	}

	@GET
	@Path("userId")
	public String userId() throws IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			return authentication.getName();
		} else {
			return "N/A";
		}
	}
}
