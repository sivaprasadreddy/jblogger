package com.sivalabs.jblogger.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Siva
 *
 */

@Entity
@Table(name = "COMMENTS")
@Data
public class Comment implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name", nullable = false, length = 150)
	@NotEmpty
	private String name;
	
	@Column(name = "email", nullable = false, length = 150)
	@NotEmpty
	@Email
	private String email;
	
	@Lob
	@Column(name = "content", nullable = false, columnDefinition="TEXT")
	@NotEmpty
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_on")
	private Date createdOn = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_on")
	private Date updatedOn;
	
	@ManyToOne
	@JoinColumn(name="post_id", nullable=false)
	private Post post;

	
}
