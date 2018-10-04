package br.com.matrix.idioma.resource;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.matrix.idioma.model.Audio;
import br.com.matrix.idioma.service.AudioService;

@RestController
@RequestMapping("/audio")
public class AudioResource {
	@Autowired
	private AudioService audioService;

	@PostMapping
	public ResponseEntity<Audio> create(@Valid @RequestBody Audio audio) {
		return new ResponseEntity<>(audioService.create(audio), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Audio> update(@RequestBody Audio audio) {
		return new ResponseEntity<>(audioService.update(audio), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Audio> findById(@PathVariable Long id) {
		return new ResponseEntity<>(audioService.findById(id), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Audio>> findAll() {
		return new ResponseEntity<>(audioService.findAll(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		audioService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
