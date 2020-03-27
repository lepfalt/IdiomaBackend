package br.com.matrix.idioma.resource;

import java.security.Principal;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.matrix.idioma.model.security.AppUser;
import br.com.matrix.idioma.model.security.AppUserDTO;
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
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<?> currentUser(Principal principal) {
		return new ResponseEntity<>(userService.currentUser(principal), HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> create(@Valid @RequestBody AppUserDTO user) {
		return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		userService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> update(@Valid @RequestBody AppUser user) {
		userService.update(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
