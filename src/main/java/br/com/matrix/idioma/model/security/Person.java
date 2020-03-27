package br.com.matrix.idioma.model.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "person")
@Data
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@NotNull
	@Size(min = 2, max = 35)
	@Column(name = "first_name")
	private String firstName;

	@NotNull
	@Size(min = 2, max = 35)
	@Column(name = "last_name")
	private String lastName;

//	@Size(min = 7, max = 100)
//	private String mail;
//
//	@NotNull
//	@Size(min = 2, max = 2)
//	@Column(name = "country_of_residence")
//	private String countryOfResidence;
//
//	private Boolean enabled;

	@JsonIgnore
	public String getCompleteName() {
		return getFirstName() + " " + getLastName();
	}

}
