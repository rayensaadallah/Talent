package com.example.talent.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.talent.models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	Optional<Users> findByUsername(String username);
	boolean existsByUsername(String email);
	List<Users> findByAuthorities_Authority(String roleName);

}
