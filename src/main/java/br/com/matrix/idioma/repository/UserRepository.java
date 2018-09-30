package br.com.matrix.idioma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matrix.idioma.model.User;

public interface UserRepository  extends JpaRepository<User, Long> {
	 
	User findByUser(Long id);
	

}
