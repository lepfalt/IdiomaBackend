package br.com.matrix.idioma.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matrix.idioma.model.Marking;

@Repository
public interface MarkingRepository  extends JpaRepository<Marking, Long> {	
	Optional<List<Marking>> findByUserIdAndAudioId(Long userId, Long audioId);	
}
