package com.sivalabs.jblogger.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sivalabs.jblogger.core.entities.User;
import org.springframework.stereotype.Repository;

/**
 * @author Siva
 * 
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{

	User findByEmail(String email);

	User findByEmailAndPassword(String email, String password);

}
