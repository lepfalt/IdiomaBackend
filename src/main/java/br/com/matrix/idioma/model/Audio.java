package br.com.matrix.idioma.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Audio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@URL
	@NotEmpty
	private String link;
	@NotEmpty
	private String description;
	@NotEmpty
	private String title;
	@NotNull
	private LocalTime duration ;
	@NotNull
	private LocalDate creationDate;
	
}
