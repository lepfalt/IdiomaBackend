package br.com.matrix.idioma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matrix.idioma.model.Audio;
import br.com.matrix.idioma.model.Marking;
import br.com.matrix.idioma.model.User;

public interface MarkingRepository  extends JpaRepository<Marking, Long> {
	
	List<Marking> findyByUserAndAudio(User user, Audio audio);
	
}
