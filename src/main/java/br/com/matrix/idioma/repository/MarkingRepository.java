package br.com.matrix.idioma.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matrix.idioma.model.Marking;

@Repository
public interface MarkingRepository  extends JpaRepository<Marking, Long> {	
	Optional<ArrayList<Marking>> findByUserIdAndAudioId(Long userId, Long audioId);	
	Optional<Marking> findByUserIdAndAudioIdAndBegin(Long userId, Long audioId, Integer begin);	
}
