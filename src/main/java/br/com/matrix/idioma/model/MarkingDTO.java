package br.com.matrix.idioma.model;

import java.time.LocalTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarkingDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Long audioId;
	@NotNull
	private Long userId;
	@NotNull
	private LocalTime begin;
	@NotNull
	private LocalTime end;
}
