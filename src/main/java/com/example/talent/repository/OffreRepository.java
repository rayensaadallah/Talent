package com.example.talent.repository;

import com.example.talent.models.Carrier;
import com.example.talent.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffreRepository extends JpaRepository<Offer, Integer> {
    Offer getByTitle(String s);

}
