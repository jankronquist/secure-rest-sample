package com.jayway.template.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Component;

@Component("userDetailsService")
public class MyUserDetailsService implements UserDetailsService, AuthenticationUserDetailsService<OpenIDAuthenticationToken> {
	@Autowired
	private Environment env;
	
	// This method called when authenticating using basic auth
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String rootUserName = env.getProperty("security.root.userName");
		if (!rootUserName.equals(rootUserName)) {
			throw new UsernameNotFoundException("not found");
		}
		return new User(username, env.getProperty("security.root.password"), retrievePrivileges(username));
	}

	// This method called when authenticating using OpenID
	@Override
	public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException {
        String email = getOpenIDEmailAttribute(token);
        if (email == null) {
			throw new UsernameNotFoundException("not found");
        }
		return new User(email, "", retrievePrivileges(email));
	}

	private List<GrantedAuthority> retrievePrivileges(String username) {
		return Arrays.<GrantedAuthority>asList();
	}

    private String getOpenIDEmailAttribute(OpenIDAuthenticationToken token) {
        List<OpenIDAttribute> attributes = token.getAttributes();
        for (OpenIDAttribute openIDAttribute : attributes) {
            if (openIDAttribute.getName().equals("email")) {
                return openIDAttribute.getValues().get(0);
            }
        }
        return null;
    }
}
