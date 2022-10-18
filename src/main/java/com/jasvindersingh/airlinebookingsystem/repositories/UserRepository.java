package com.jasvindersingh.airlinebookingsystem.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jasvindersingh.airlinebookingsystem.models.User;

public interface UserRepository extends JpaRepository<User,Long> {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);
}
