package br.com.matrix.idioma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matrix.idioma.model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
	 
	User findByUser(Long id);
	

}
