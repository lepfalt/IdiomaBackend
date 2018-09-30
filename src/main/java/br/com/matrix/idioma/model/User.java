package br.com.matrix.idioma.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
	
	private Long id;
	private String login;
	private String password;
	private String email;
	private String name;

}
