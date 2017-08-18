package com.sivalabs.jblogger.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author Siva
 *
 */
@Entity
@Table(name = "TAGS")
@Data
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
	public int compareTo(Tag other)
	{
		return this.label.compareToIgnoreCase(other.label);
	}

}
