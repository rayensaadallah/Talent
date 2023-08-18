package com.example.talent.repository;

import com.example.talent.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepsitory extends JpaRepository<Project, Integer> {
}
