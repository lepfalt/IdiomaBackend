package br.com.matrix.idioma.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.matrix.idioma.model.Audio;
import br.com.matrix.idioma.repository.AudioRepository;

public class AudioService {
	
	@Autowired
	private AudioRepository audioRepository;
	
	public Audio create(Audio audio) {
		
		return audioRepository.save(audio);
	}
	
	public Audio update(Audio audio) {
		
		return audioRepository.save(audio);
	}

	
	public List<Audio> findAll(){
		
		return audioRepository.findAll();
		
	}
	
	public Optional<Audio> findOne(Long id) {
		return audioRepository.findById(id);
		
	}
	
	public void delete(Long id) {
		 audioRepository.deleteById(id);
		
	}
}
