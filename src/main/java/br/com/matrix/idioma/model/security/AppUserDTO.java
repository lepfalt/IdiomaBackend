package br.com.matrix.idioma.model.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserDTO {
	
	private String email;
	private String password;
	private Person personData;
}
