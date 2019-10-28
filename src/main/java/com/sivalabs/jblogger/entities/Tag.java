package com.sivalabs.jblogger.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Table(name = "TAGS")
@Data
public class Tag implements Serializable, Comparable<Tag>
{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="tag_id_generator", sequenceName="tag_id_seq", initialValue = 100, allocationSize=1)
	@GeneratedValue(generator = "tag_id_generator")
	private Long id;

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
