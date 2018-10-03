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

import br.com.matrix.idioma.model.Audio;
import br.com.matrix.idioma.model.Marking;
import br.com.matrix.idioma.model.User;
import br.com.matrix.idioma.service.MarkingService;

@RestController
@RequestMapping("marking")
public class MarkingResource {
	@Autowired
	private MarkingService markingService;
	
	@GetMapping()
	public ResponseEntity<?> findById(Long id) {
		return new ResponseEntity<>(markingService.findById(id), HttpStatus.OK);
	}
	@GetMapping(path = "/findByUserAndAudio")
	public ResponseEntity<?> findByUserAndAudio(@RequestParam("user") User user, @RequestParam("audio") Audio audio) {
		return new ResponseEntity<>(markingService.findByUserAndAudio(user, audio), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Marking marking) {
		return new ResponseEntity<>(markingService.create(marking), HttpStatus.CREATED);
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
