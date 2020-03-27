package br.com.matrix.idioma.resource;

import java.security.Principal;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.matrix.idioma.model.Marking;
import br.com.matrix.idioma.model.MarkingDTO;
import br.com.matrix.idioma.model.security.AppUser;
import br.com.matrix.idioma.repository.AppUserRepository;
import br.com.matrix.idioma.service.MarkingService;

@RestController
@RequestMapping("/marking")
public class MarkingResource {
	@Autowired
	private MarkingService markingService;
	@Autowired
	private AppUserRepository userRepository;

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return new ResponseEntity<>(markingService.findById(id), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> findByUserAndAudio(Principal principal,
			@RequestParam(name = "audioId", required = true) Long audioId) {
		return new ResponseEntity<>(markingService.findByUserIdAndAudioId(userRepository.findByEmail(principal.getName()).get().getId(), audioId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody MarkingDTO markingDTO, Principal principal) {
		Optional <AppUser> user = userRepository.findByEmail(principal.getName());
		if(user.isPresent()) {			
			markingDTO.setUserId(user.get().getId());			
			return new ResponseEntity<>(markingService.create(markingDTO), HttpStatus.CREATED);
		}
		return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		markingService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@Valid @RequestBody Marking marking) {
		markingService.update(marking);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
