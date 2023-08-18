package com.example.talent.repository;

import com.example.talent.models.Cv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CVRepository  extends JpaRepository<Cv, Integer> {
}
