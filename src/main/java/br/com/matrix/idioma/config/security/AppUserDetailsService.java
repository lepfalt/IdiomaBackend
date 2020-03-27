package br.com.matrix.idioma.config.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.matrix.idioma.model.security.AppUser;
import br.com.matrix.idioma.model.security.SystemUser;
import br.com.matrix.idioma.repository.AppUserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private AppUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AppUser> userOptional = userRepository.findByEmail(username);
		AppUser user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		return new SystemUser(user, getPermissions(user));
	}
	
	private Collection<? extends GrantedAuthority> getPermissions(AppUser user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getPermissions().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription())));
		return authorities;
	}
	
}
