package com.example.talent.repository;

import com.example.talent.models.Cv;
import com.example.talent.models.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SkillsRepository extends JpaRepository<Skills,Integer> {
    Skills findByNameIgnoreCase(String name);
    List<Skills> findAllByCv(Cv cv);
}
