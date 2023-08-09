package com.example.talent.repository;

import com.example.talent.models.Carrier;
import com.example.talent.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrierRepository extends JpaRepository<Carrier, Integer > {
}
