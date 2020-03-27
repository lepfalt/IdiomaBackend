package br.com.matrix.idioma.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
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
	private String englishTitle;
	@NotEmpty
	private String portugueseTitle;
	@NotNull
	private LocalTime duration ;
	@URL
	@NotEmpty
	private String englishSubtitle;	
	@URL
	@NotEmpty
	private String portugueseSubtitle;
	private LocalDate creationDate;
	@NotEmpty
	@Column(length = 5000)
	private String englishTranscription;
	@NotEmpty
	@Column(length = 5000)
	private String portugueseTranscription;
	
	
	
	public Audio() {
		LocalDate date = LocalDate.now();
		this.setCreationDate(date);
	}
	
	
	
	
	
}
