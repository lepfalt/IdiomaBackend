package br.com.matrix.idioma.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Marking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private Audio audio;
	@OneToOne
	private User user;
	@NotNull
	private LocalTime begin;
	@NotNull
	private LocalTime end;

}
