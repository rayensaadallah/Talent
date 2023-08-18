package com.example.talent.repository;

import com.example.talent.models.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SkillsRepository extends JpaRepository<Skills,Integer> {
    Skills findByNameIgnoreCase(String name);
}
