/**
 * 
 */
package com.sivalabs.jblogger.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.Objects;

/**
 * @author Siva
 *
 */
@Entity
@Table(name = "COMMENTS")
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

	@Override
	public int hashCode(){
	    return Objects.hashCode(id);
	}

	@Override
	public boolean equals(final Object obj){
	    if(obj instanceof Comment){
	        final Comment other = (Comment) obj;
	        return id == other.id;
	    } else{
	        return false;
	    }
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
}
