/**
 * 
 */
package com.sivalabs.jblogger.search;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.jblogger.core.entities.Post;
import com.sivalabs.jblogger.core.repositories.PostRepository;

/**
 * @author Siva
 *
 */
@Profile("solr")
@Service
@Transactional
public class SolrBlogSearch implements BlogSearch
{
	private static final Logger logger = LoggerFactory.getLogger(SolrBlogSearch.class); 
	
	@Autowired private SolrPostRepository solrPostRepository;
	@Autowired private PostRepository postRepository;
	
	@Override
	public void index(List<Post> posts) {
		logger.info("Starting Solr Indexing of posts");
		if(posts != null)
		{
			for (Post post : posts) {
				PostDocument doc = new PostDocument();
				doc.setId(post.getId());
				doc.setTitle(post.getTitle());
				doc.setShortDescription(post.getShortDescription());
				doc.setUrl(post.getUrl());
				doc.setContent(post.getContent());
				
				solrPostRepository.save(doc);
			}
		}
	}

	@Override
	public List<Post> search(String query) {
		logger.info("Searching posts in Solr");
		List<PostDocument> docs = solrPostRepository.findByContentContains(query);
		List<Post> posts = new ArrayList<>();
		if(docs != null)
		{
			for (PostDocument postDocument : docs) {
				posts.add(postRepository.findOne(postDocument.getId()));
			}
		}
		return posts;
	}

}
