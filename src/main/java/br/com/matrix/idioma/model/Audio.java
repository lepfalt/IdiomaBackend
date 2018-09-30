package br.com.matrix.idioma.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Audio {

	private String link;
	private String description;
	private Long id;
	private String title;
	private LocalDateTime duration;
	private LocalDate creationDate;
	
}
