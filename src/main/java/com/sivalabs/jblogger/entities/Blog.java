/**
 * 
 */
package com.sivalabs.jblogger.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.Objects;

/**
 * @author Siva
 *
 */
@Entity
@Table(name="blog")
public class Blog implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable=false, unique=true)
	@NotEmpty()
	private String name;
	
	@Column
	private String description;
	
	private String welcomeNote;
	
	@Override
	public int hashCode(){
	    return Objects.hashCode(id, name);
	}

	@Override
	public boolean equals(final Object obj){
	    if(obj instanceof Blog){
	        final Blog other = (Blog) obj;
	        return Objects.equal(name, other.name)
	            && id == other.id // special handling for primitives
	            ;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWelcomeNote() {
		return welcomeNote;
	}
	public void setWelcomeNote(String welcomeNote) {
		this.welcomeNote = welcomeNote;
	}
	
}
