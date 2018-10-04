package br.com.matrix.idioma.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matrix.idioma.model.Marking;
import br.com.matrix.idioma.model.MarkingDTO;
import br.com.matrix.idioma.repository.MarkingRepository;

@Service 
public class MarkingService {
	
	@Autowired
	private MarkingRepository markingRepository;
	
	@Autowired
	private AudioService audioService;
	@Autowired
	private UserService userService;
	
	public Marking create(MarkingDTO markingDTO) {
		Marking marking = new Marking();
		BeanUtils.copyProperties(markingDTO, marking);
		marking.setAudio(audioService.findById(markingDTO.getAudioId()));
		marking.setUser(userService.findById(markingDTO.getUserId()));
		return markingRepository.save(marking);
	}
	
	public Optional<Marking> findById(Long id) {
		return markingRepository.findById(id);
	}
	
	public List<Marking> findAll(){
		return markingRepository.findAll();
	}
	
	public Optional<List<Marking>> findByUserIdAndAudioId(Long userId, Long audioId){
		return markingRepository.findByUserIdAndAudioId(userId, audioId);
	}
	
	public Marking update(Marking marking) {
		return markingRepository.save(marking);
	}
	
	public void deleteById(Long id) {
		markingRepository.deleteById(id);
	}

}
