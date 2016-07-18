/**
 * 
 */
package com.sivalabs.jblogger.search;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.sivalabs.jblogger.core.entities.Post;
import com.sivalabs.jblogger.core.repositories.PostRepository;

/**
 * @author Siva
 *
 */
@Profile("!solr")
@Service
public class DBBlogSearch implements BlogSearch
{
	private static final Logger logger = LoggerFactory.getLogger(DBBlogSearch.class); 
	
	private PostRepository postRepository;

	@Autowired
	public DBBlogSearch(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public void index(List<Post> posts) {
		logger.info("Starting DB Indexing of posts");
	}

	@Override
	public List<Post> search(String query) {
		logger.info("Searching posts in DB");
		return postRepository.searchPosts("%"+query+"%");
	}

}
