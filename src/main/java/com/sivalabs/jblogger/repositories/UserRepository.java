package com.sivalabs.jblogger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sivalabs.jblogger.entities.User;

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
