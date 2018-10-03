package br.com.matrix.idioma.model;

import java.time.LocalTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarkingDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long audioId;
	private Long userId;
	@NotEmpty
	private LocalTime begin;
	@NotEmpty
	private LocalTime end;
}
