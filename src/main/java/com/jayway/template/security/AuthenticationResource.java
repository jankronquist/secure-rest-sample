package com.jayway.template.security;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Path("/authentication")
@Component
public class AuthenticationResource {
	@GET
	@Path("status")
	public UserStatus status() throws IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserStatus status = new UserStatus();

		if (authentication != null) {
            status.user = authentication.getName();
            status.authenticated = true;
		} else {
            status.user = "";
            status.authenticated = false;
		}

        return status;
	}
}
