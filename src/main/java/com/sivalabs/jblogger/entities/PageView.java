package com.sivalabs.jblogger.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
import lombok.Data;

/**
 * @author Siva
 *
 */
@Entity
@Table(name="pageviews")
@Data
public class PageView implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="pageview_generator", sequenceName="pageview_sequence", initialValue = 100)
	@GeneratedValue(generator = "pageview_generator")
	private Integer id;
	
	private String url;
	
	private String referrer;
	
	@Column(name="visit_time")
	private LocalDateTime visitTime = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post post;

}
