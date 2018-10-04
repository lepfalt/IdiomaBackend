package br.com.matrix.idioma.resource;

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
import br.com.matrix.idioma.service.MarkingService;

@RestController
@RequestMapping("/marking")
public class MarkingResource {
	@Autowired
	private MarkingService markingService;

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return new ResponseEntity<>(markingService.findById(id), HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<?> findByUserAndAudio(@RequestParam(name = "userId", required = true) Long userId,
			@RequestParam(name = "audioId", required = true) Long audioId) {
		return new ResponseEntity<>(markingService.findByUserIdAndAudioId(userId, audioId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody MarkingDTO markingDTO) {
		return new ResponseEntity<>(markingService.create(markingDTO), HttpStatus.CREATED);
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
