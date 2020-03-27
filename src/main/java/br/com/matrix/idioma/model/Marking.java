package br.com.matrix.idioma.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.com.matrix.idioma.model.security.AppUser;
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
	private AppUser user;
	@NotNull
	private Integer begin;
	@NotNull
	private Integer end;

}
