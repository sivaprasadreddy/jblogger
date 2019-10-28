package com.sivalabs.jblogger.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name="pageviews")
@Data
public class PageView implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="pageview_id_generator", sequenceName="pageview_id_seq", initialValue = 100, allocationSize=1)
	@GeneratedValue(generator = "pageview_id_generator")
	private Long id;
	
	private String url;
	
	private String referrer;
	
	@Column(name="visit_time")
	private LocalDateTime visitTime = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post post;

}
