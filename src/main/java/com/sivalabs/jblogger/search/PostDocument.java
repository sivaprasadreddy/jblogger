/**
 * 
 */
package com.sivalabs.jblogger.search;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;
/**
 * @author Siva
 *
 */
@SolrDocument(solrCoreName = "postscore")
public class PostDocument {
	@Id
	@Indexed
	private Integer id;
	@Field()
	@Indexed(type = "string")
	private String title;
	@Field
	@Indexed(type = "string")
	private String url;
	@Field
	@Indexed(type = "string")
	private String shortDescription;
	@Field
	@Indexed(type = "string")
	private String content;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
