/**
 * 
 */
package com.sivalabs.jblogger.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sivalabs.jblogger.core.entities.Role;
import org.springframework.stereotype.Repository;

/**
 * @author Siva
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>
{

	Role findByName(String name);

}
