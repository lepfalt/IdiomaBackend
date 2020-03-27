package br.com.matrix.idioma.model.security;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.matrix.idioma.model.Permission;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column(name = "email")
	private String email;

	@JsonIgnore
	@Column(name = "password")
	private String password;

	@JsonIgnoreProperties({ "client" })
	@Valid
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "person_data_id")
	private Person personData;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="user_permission", 
		joinColumns=@JoinColumn(name="user_id"), 
		inverseJoinColumns= @JoinColumn(name="permission_id"))
	private List<Permission> permissions;

	private Boolean pending;

	private Boolean enabled;

	@JsonIgnore
	public boolean isEnabled() {
		return getEnabled() && !getPending();
	}

}
