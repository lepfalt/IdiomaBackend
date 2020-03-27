package br.com.matrix.idioma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="permission")
@Data
public class Permission {

	@Id
	private Long id;
	
	@Column(name="description")
	private String description;
	
	
}
