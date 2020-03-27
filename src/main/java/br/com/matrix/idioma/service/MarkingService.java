package br.com.matrix.idioma.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matrix.idioma.config.ResourceNotFoundException;
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

	public MarkingDTO findById(Long id) {
		notFoundId(id);
		MarkingDTO markingDTO = new MarkingDTO();
		Marking marking = markingRepository.findById(id).get();
		BeanUtils.copyProperties(marking, markingDTO);
		markingDTO.setAudioId(marking.getAudio().getId());
		markingDTO.setUserId(marking.getUser().getId());
		return markingDTO;
	}

	public List<Marking> findAll() {
		return markingRepository.findAll();
	}

	public Optional<List<MarkingDTO>> findByUserIdAndAudioId(Long userId, Long audioId) {
		audioService.notFoundId(audioId);
		userService.notFoundId(userId);

		Optional<ArrayList<Marking>> marking = markingRepository.findByUserIdAndAudioId(userId, audioId);

		if (marking.isPresent()) {
			return Optional.ofNullable(marking.get().stream().map(m -> {
				MarkingDTO markingDTO = new MarkingDTO();
				BeanUtils.copyProperties(m, markingDTO);
				markingDTO.setAudioId(m.getAudio().getId());
				markingDTO.setUserId(m.getUser().getId());
				return markingDTO;
			}).collect(Collectors.toList()));
		}
		List<MarkingDTO> listEmpty = new ArrayList<MarkingDTO>();
		return Optional.ofNullable(listEmpty);
	}

	public Marking update(Marking marking) {
		return markingRepository.save(marking);
	}

	public void deleteById(Long id) {
		notFoundId(id);
		markingRepository.deleteById(id);
	}

	public void notFoundId(Long id) {
		if (markingRepository.existsById(id) == false)
			throw new ResourceNotFoundException("A marcação não existe.");
	}

	public boolean existsMarking(MarkingDTO marking) {
		if (markingRepository
				.findByUserIdAndAudioIdAndBegin(marking.getUserId(), marking.getAudioId(), marking.getBegin())
				.isPresent())
			return true;
		return false;
	}

}
