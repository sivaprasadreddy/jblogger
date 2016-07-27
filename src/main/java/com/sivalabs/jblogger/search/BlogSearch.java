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

	void index(List<Post> posts);
	List<Post> search(String query);
	
}
