package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {

	UserDetails findByName(String name);
}
