/**
 * 
 */
package com.sivalabs.jblogger.search;

import java.util.List;

import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 * @author Siva
 *
 */
public interface SolrPostRepository extends SolrCrudRepository<PostDocument, Integer> {
	
	//@Query("title:*?0* OR content:*?0*")
	@Query("collector:*?0*")
	public List<PostDocument> findByContentContains(String query);
}
