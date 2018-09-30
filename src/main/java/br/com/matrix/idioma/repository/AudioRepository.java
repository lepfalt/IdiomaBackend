package br.com.matrix.idioma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matrix.idioma.model.Audio;
@Repository
public interface AudioRepository  extends JpaRepository<Audio, Long> {

	
}
