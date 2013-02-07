package com.jayway.template.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

@Configuration
@ComponentScan("com.jayway.template")
@ImportResource("classpath:applicationContext-security.xml")
@PropertySource("web-default.properties")
public class WebConfig {
	static {
		LogInitializer.initializeJavaUtilLoggingBridge();
	}
	
	@Bean(name="passwordEncoder")
	@Autowired
	public PasswordEncoder passwordEncoder(Environment env) {
		return new StandardPasswordEncoder(env.getProperty("security.secret"));
	}

    @Bean(name="ep403")
    public Http403ForbiddenEntryPoint entryPoint() {
        return new Http403ForbiddenEntryPoint();
    }
}
