package com.jayway.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan("com.jayway.rest")
@ImportResource("classpath:applicationContext-security.xml")
public class WebConfig {

	@Bean(name="passwordEncoder")
	public PasswordEncoder passwordEncoder() {
//		return new StandardPasswordEncoder();
		return NoOpPasswordEncoder.getInstance();
	}
}
