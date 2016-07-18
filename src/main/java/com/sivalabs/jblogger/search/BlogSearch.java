/**
 * 
 */
package com.sivalabs.jblogger.search;

import java.util.List;

import com.sivalabs.jblogger.core.entities.Post;

/**
 * @author Siva
 *
 */
public interface BlogSearch {

	public void index(List<Post> posts);
	public List<Post> search(String query);
	
}
