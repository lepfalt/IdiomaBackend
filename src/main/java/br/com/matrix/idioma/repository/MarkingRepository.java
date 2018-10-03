package br.com.matrix.idioma.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matrix.idioma.model.Audio;
import br.com.matrix.idioma.model.Marking;
import br.com.matrix.idioma.model.User;

@Repository
public interface MarkingRepository  extends JpaRepository<Marking, Long> {	
	Optional<Marking> findByUserAndAudio(User user, Audio audio);	
}
