package br.com.matrix.idioma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matrix.idioma.model.Audio;

public interface AudioRepository  extends JpaRepository<Audio, Long> {

	Audio findyByAudio(long id);
	
}
