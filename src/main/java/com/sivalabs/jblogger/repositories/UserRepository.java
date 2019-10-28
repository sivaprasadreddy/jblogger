package com.sivalabs.jblogger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sivalabs.jblogger.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	Optional<User> findByEmail(String email);
}
