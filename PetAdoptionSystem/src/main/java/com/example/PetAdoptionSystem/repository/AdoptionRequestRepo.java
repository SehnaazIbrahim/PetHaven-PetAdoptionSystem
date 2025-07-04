package com.example.PetAdoptionSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PetAdoptionSystem.model.AdoptionRequest;

@Repository
public interface AdoptionRequestRepo extends JpaRepository<AdoptionRequest,Integer> {
  List<AdoptionRequest> findByUserId(int userId);
  List<AdoptionRequest> findByPetId(int petId);
}
