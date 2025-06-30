package com.example.PetAdoptionSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PetAdoptionSystem.model.AdoptionRequest;

@Repository
public interface AdoptionRequestRepo extends JpaRepository<AdoptionRequest,Integer> {

}
