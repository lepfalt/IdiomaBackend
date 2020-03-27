package br.com.matrix.idioma.model.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

public class SystemUser extends User {

	private static final long serialVersionUID = 1L;

	@Getter
	private AppUser otcUser;

	public SystemUser(AppUser apiUser, Collection<? extends GrantedAuthority> authorities) {
		super(apiUser.getEmail(), apiUser.getPassword(), authorities);
		this.otcUser = apiUser;
	}

}
