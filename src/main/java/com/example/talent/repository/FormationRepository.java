package com.example.talent.repository;

import com.example.talent.models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationRepository extends JpaRepository<Formation,Integer> {
    Formation getFormationByTitle(String title);
}
