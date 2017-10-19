package com.sivalabs.jblogger.entities;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

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
	@SequenceGenerator(name="comment_generator", sequenceName="comment_sequence", initialValue = 100)
	@GeneratedValue(generator = "comment_generator")
	private Integer id;
	
	@Column(name = "name", nullable = false, length = 150)
	@NotEmpty
	private String name;
	
	@Column(name = "email", nullable = false, length = 150)
	@NotEmpty
	@Email
	private String email;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "content", nullable = false)
	@NotEmpty
	private String content;
	
	@Column(name="created_on")
	private LocalDateTime createdOn = LocalDateTime.now();
	
	@Column(name="updated_on")
	private LocalDateTime updatedOn;
	
	@ManyToOne
	@JoinColumn(name="post_id", nullable=false)
	private Post post;

	
}
