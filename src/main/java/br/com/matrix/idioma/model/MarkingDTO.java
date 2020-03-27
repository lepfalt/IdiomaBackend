package br.com.matrix.idioma.model;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarkingDTO {
	
	private Long id;
	@NotNull
	private Long audioId;
	private Long userId;
	@NotNull
	private Integer begin;
	@NotNull
	private Integer end;
}
