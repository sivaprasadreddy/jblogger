/**
 * 
 */
package com.sivalabs.jblogger.core.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.Objects;

/**
 * @author Siva
 *
 */
@Entity
@Table(name = "POSTS")
public class Post implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "title", nullable = false, length = 150)
	@NotEmpty
	private String title;
	
	@Column(name = "url", length = 255)
	private String url;

	//@Lob
	//@Type(type = "org.hibernate.type.TextType")
	@Column(name = "content", nullable = false, columnDefinition = "text")
	@NotEmpty
	private String content;

	@Column(name = "short_desc", length=500)
	@NotEmpty
	private String shortDescription;
	
	@ManyToOne
	@JoinColumn(name = "created_by", nullable = false)
	private User createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on")
	private Date createdOn = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_on")
	private Date updatedOn;

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

	@Override
	public int hashCode(){
	    return Objects.hashCode(id);
	}

	@Override
	public boolean equals(final Object obj){
	    if(obj instanceof Post){
	        final Post other = (Post) obj;
	        return id == other.id;
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

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public User getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(User createdBy)
	{
		this.createdBy = createdBy;
	}

	public Date getCreatedOn()
	{
		return createdOn;
	}

	public void setCreatedOn(Date createdOn)
	{
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn()
	{
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn)
	{
		this.updatedOn = updatedOn;
	}

	public List<Comment> getComments()
	{
		return comments;
	}

	public void setComments(List<Comment> comments)
	{
		this.comments = comments;
	}

	public String getShortDescription()
	{
		return shortDescription;
	}

	public void setShortDescription(String shortDescription)
	{
		this.shortDescription = shortDescription;
	}

	public Long getViewCount()
	{
		return viewCount;
	}

	public void setViewCount(Long viewCount)
	{
		this.viewCount = viewCount;
	}

	public Set<Tag> getTags()
	{
		return tags;
	}

	public void setTags(Set<Tag> tags)
	{
		this.tags = tags;
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

}
