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
import org.springframework.web.bind.annotation.RestController;

import br.com.matrix.idioma.model.User;
import br.com.matrix.idioma.service.UserService;

@RestController
@RequestMapping("/user")
public class UserResource {
	@Autowired
	private UserService userService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody User user) {
		return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		userService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@Valid @RequestBody User user) {
		userService.update(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
