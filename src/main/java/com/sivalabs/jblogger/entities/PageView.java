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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.common.base.Objects;

/**
 * @author Siva
 *
 */
@Entity
@Table(name="pageviews")
public class PageView implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String url;
	
	private String referrer;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="visit_time")
	private Date visitTime = new Date();
	
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post post;
	
	@Override
	public int hashCode(){
	    return Objects.hashCode(id);
	}

	@Override
	public boolean equals(final Object obj){
	    if(obj instanceof PageView){
	        final PageView other = (PageView) obj;
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
	
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public Post getPost()
	{
		return post;
	}
	public void setPost(Post post)
	{
		this.post = post;
	}
	public Date getVisitTime()
	{
		return visitTime;
	}
	public void setVisitTime(Date visitTime)
	{
		this.visitTime = visitTime;
	}
	public String getReferrer()
	{
		return referrer;
	}
	public void setReferrer(String referrer)
	{
		this.referrer = referrer;
	}
	
	
}
