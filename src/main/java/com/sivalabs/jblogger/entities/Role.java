package com.sivalabs.jblogger.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name="roles")
@Data
public class Role implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="role_id_generator", sequenceName="role_id_seq", initialValue = 100, allocationSize=1)
	@GeneratedValue(generator = "role_id_generator")
	private Long id;
	
	@Column(nullable=false, unique=true)
	@NotEmpty
	private String name;
		
	@ManyToMany(mappedBy="roles")
	private List<User> users;

}
