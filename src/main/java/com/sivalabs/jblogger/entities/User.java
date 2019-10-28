package com.sivalabs.jblogger.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Data
public class User implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="user_id_generator", sequenceName="user_id_seq", initialValue = 100, allocationSize=1)
	@GeneratedValue(generator = "user_id_generator")
	private Long id;
	
	@Column(nullable=false)
	@NotEmpty()
	private String name;
	
	@Column(nullable=false, unique=true)
	@NotEmpty
	@Email(message="{errors.invalid_email}")
	private String email;
	
	@Column(nullable=false)
	@NotEmpty
	@Size(min=4)
	private String password;
	
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
	      name="user_role",
	      joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
	      inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
	private List<Role> roles = new ArrayList<>();
	
}
