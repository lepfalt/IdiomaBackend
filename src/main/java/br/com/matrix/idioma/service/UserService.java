package br.com.matrix.idioma.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import br.com.matrix.idioma.model.User;
import br.com.matrix.idioma.repository.UserRepository;

public class UserService {

	@Autowired
	UserRepository userRepository;

	public User create(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);	
	}

	public User update(User user) {
		return userRepository.save(user);
	}
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

}
