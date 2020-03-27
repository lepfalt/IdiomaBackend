package br.com.matrix.idioma.service;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.matrix.idioma.config.ResourceNotFoundException;
import br.com.matrix.idioma.config.ResourceObjectRegisteredException;
import br.com.matrix.idioma.config.security.ResourceServerConfig;
import br.com.matrix.idioma.model.Marking;
import br.com.matrix.idioma.model.security.AppUser;
import br.com.matrix.idioma.model.security.AppUserDTO;
import br.com.matrix.idioma.repository.AppUserRepository;

@Service
public class UserService {
	
	@Autowired
	AppUserRepository userRepository;
	
	@Autowired
	ResourceServerConfig resourceServerConfig;
	
	public AppUser create(AppUserDTO userDTO) {
		AppUser newUser = new AppUser();
		BeanUtils.copyProperties(userDTO, newUser);
		BCryptPasswordEncoder passwordEncoder = resourceServerConfig.passwordEncoder();		
		newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));		
		existsUser(newUser);
		return userRepository.save(newUser);
	}
	
	public AppUser findById(Long id) {
		notFoundId(id);
		return userRepository.findById(id).orElse(null);
	}
	
	public AppUser update(AppUser user) {
		return userRepository.save(user);
	}
	
	public Optional<AppUser> currentUser(Principal principal) {
		Optional<AppUser> currentUser = userRepository.findByEmail(principal.getName());
		return currentUser;
	}
	
	
	public void deleteById(Long id) {
		notFoundId(id);
		userRepository.deleteById(id);
	
	}
	
	public void notFoundId(Long id) {
		if (!userRepository.existsById(id))
			throw new ResourceNotFoundException("Usuário não existe");
	}
	
	public void existsUser(AppUser user) {
		if(userRepository.findByEmail(user.getEmail()).isPresent())
			throw new ResourceObjectRegisteredException("Esse email já está cadastrado");
	}
	
}
