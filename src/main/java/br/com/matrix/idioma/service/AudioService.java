package br.com.matrix.idioma.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.matrix.idioma.model.Audio;
import br.com.matrix.idioma.repository.AudioRepository;

public class AudioService {
	
	@Autowired
	private AudioRepository audioRepository;
	
	public Audio create(Audio audio) {
		
		return audioRepository.save(audio);
	}
	
	

}
