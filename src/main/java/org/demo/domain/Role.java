package org.demo.domain;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("unused")
public enum Role implements GrantedAuthority {

	ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

	private final String description;

	Role(final String description) {
		this.description = description;
	}

	@Override
	public String getAuthority() {
		return this.description;
	}

}
