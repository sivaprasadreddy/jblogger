package com.sivalabs.jblogger.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "POSTS")
@Data
public class Post implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="post_id_generator", sequenceName="post_id_seq", initialValue = 100, allocationSize=1)
	@GeneratedValue(generator = "post_id_generator")
	private Long id;

	@Column(name = "title", nullable = false, length = 150)
	@NotEmpty
	private String title;
	
	@Column(name = "url", length = 255)
	private String url;

	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "content", nullable = false)
	@NotEmpty
	private String content;

	@Column(name = "short_desc", length=500)
	@NotEmpty
	private String shortDescription;
	
	@ManyToOne
	@JoinColumn(name = "created_by", nullable = false)
	private User createdBy;

	@Column(name = "created_on")
	private LocalDateTime createdOn = LocalDateTime.now();

	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	@Column(name = "view_count", columnDefinition="bigint default 0")
	private Long viewCount = 0L;
	
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
	      name="post_tag",
	      joinColumns={@JoinColumn(name="POST_ID", referencedColumnName="ID")},
	      inverseJoinColumns={@JoinColumn(name="TAG_ID", referencedColumnName="ID")})
	private Set<Tag> tags;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<Comment> comments = new ArrayList<>();

	public String getTagIdsAsString()
	{
		if(tags == null || tags.isEmpty()) return "";

		StringBuilder sb = new StringBuilder();
		for (Tag tag : tags) {
			sb.append(","+tag.getId());
		}
		return sb.substring(1);
	}

	public String getTagsAsString()
	{
		if(tags == null || tags.isEmpty()) return "";
		
		StringBuilder sb = new StringBuilder();
		for (Tag tag : tags) {
			sb.append(","+tag.getLabel());
		}
		return sb.substring(1);
	}
	
	public String[] getTagsAsStringArray()
	{
		if(tags == null || tags.isEmpty()) return new String[]{};
		
		String[] arr = new String[tags.size()];
		int i = 0;
		for (Tag tag : tags)
		{
			arr[i++] = tag.getLabel();
		}
				
		return arr;
	}

	public String[] getTagIdsAsStringArray()
	{
		if(tags == null || tags.isEmpty()) return new String[]{};

		String[] arr = new String[tags.size()];
		int i = 0;
		for (Tag tag : tags)
		{
			arr[i++] = ""+tag.getId();
		}

		return arr;
	}

}
