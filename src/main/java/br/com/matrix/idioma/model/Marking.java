package br.com.matrix.idioma.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Marking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@NotEmpty
	private Audio audio;
	@OneToOne
	@NotEmpty
	private User user;
	@NotEmpty
	private LocalTime begin;
	@NotEmpty
	private LocalTime end;

}
