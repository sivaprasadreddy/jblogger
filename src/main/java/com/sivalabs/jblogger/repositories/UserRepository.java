package com.sivalabs.jblogger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sivalabs.jblogger.entities.User;

import java.util.Optional;

/**
 * @author Siva
 * 
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
	Optional<User> findByEmail(String email);
}
