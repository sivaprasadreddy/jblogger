package com.sivalabs.jblogger.config;
/*
import java.io.File;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.core.CoreContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.util.ResourceUtils;

@Configuration
@EnableSolrRepositories(basePackages="com.sivalabs.jblogger.search")
public class AppConfig
{

	@Bean
	public SolrClient solrClientEmbedded() throws Exception
	{		
		
		String solrHome = ResourceUtils.getURL("classpath:solr").getPath();
	    CoreContainer container = CoreContainer.createAndLoad(new File(solrHome).toPath(),  new File(solrHome + "/solr.xml").toPath());

	    return new EmbeddedSolrServer(container, "postscore");
	}

	@Bean(name="solrTemplate")
	public SolrOperations solrTemplateEmbedded() throws Exception
	{
		return new SolrTemplate(solrClientEmbedded());
	}
}
*/