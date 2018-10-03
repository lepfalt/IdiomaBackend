package br.com.matrix.idioma.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matrix.idioma.model.Audio;
import br.com.matrix.idioma.model.Marking;
import br.com.matrix.idioma.model.User;
import br.com.matrix.idioma.repository.MarkingRepository;

@Service 
public class MarkingService {
	
	@Autowired
	MarkingRepository markingRepository;
	
	public Marking create(Marking marking) {
		return markingRepository.save(marking);
	}
	
	public Optional<Marking> findById(Long id) {
		return markingRepository.findById(id);
	}
	
	public Optional<Marking> findByUserAndAudio(User user, Audio audio){
		return markingRepository.findByUserAndAudio(user, audio);
	}
	
	public Marking update(Marking marking) {
		return markingRepository.save(marking);
	}
	
	public void deleteById(Long id) {
		markingRepository.deleteById(id);
	}

}
