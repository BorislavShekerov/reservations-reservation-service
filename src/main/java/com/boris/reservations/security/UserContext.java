package com.boris.reservations.security;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;

public class UserContext {
    protected final String email;
    protected final String firstName;
    protected final String lastName;
    protected final List<GrantedAuthority> authorities;
    protected final Map<String, Object> extendedAttributes;
    
    UserContext(String email, String firstName, String lastName, Map<String, Object> extendedAttributes, List<GrantedAuthority> authorities) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.authorities = authorities;
		this.extendedAttributes = extendedAttributes;
	}

	public static UserContext create(String email, String firstName, String lastName, Map<String, Object> extendedAttributes, List<GrantedAuthority> authorities) {
        if (StringUtils.isBlank(email) || StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName)) throw new IllegalArgumentException("Email is blank: " + email);
        return new UserContext(email, firstName, lastName, extendedAttributes, authorities);
    }

    public String getEmail() {
        return email;
    }
    
    public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }
	
	public Map<String, Object> getExtendedAttributes(){
		return extendedAttributes;
	}
}
