package com.jayway.template.security;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserStatus {
    public String user;
    public boolean authenticated;

    public UserStatus() {}

    public UserStatus(String user, boolean authenticated) {
        this.user = user;
        this.authenticated = authenticated;
    }
}
