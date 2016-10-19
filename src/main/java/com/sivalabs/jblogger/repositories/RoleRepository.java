/**
 * 
 */
package com.sivalabs.jblogger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sivalabs.jblogger.entities.Role;

/**
 * @author Siva
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>
{

	Role findByName(String name);

}
