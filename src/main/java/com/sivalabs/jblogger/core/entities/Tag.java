/**
 * 
 */
package com.sivalabs.jblogger.core.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

/**
 * @author Siva
 *
 */
@Entity
@Table(name = "TAGS")
public class Tag implements Serializable, Comparable<Tag>
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "label", unique=true, nullable = false, length = 150)
	private String label;
	
	@JsonIgnore
	@ManyToMany(mappedBy="tags")
	private List<Post> posts;

	@Override
	public int hashCode(){
	    return Objects.hashCode(id, label);
	}

	@Override
	public boolean equals(final Object obj){
	    if(obj instanceof Tag){
	        final Tag other = (Tag) obj;
	        return Objects.equal(label, other.label)
		            && id == other.id;
	    } else{
	        return false;
	    }
	}
	
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public List<Post> getPosts()
	{
		return posts;
	}

	public void setPosts(List<Post> posts)
	{
		this.posts = posts;
	}

	@Override
	public int compareTo(Tag other)
	{
		return this.label.compareToIgnoreCase(other.label);
	}

}
