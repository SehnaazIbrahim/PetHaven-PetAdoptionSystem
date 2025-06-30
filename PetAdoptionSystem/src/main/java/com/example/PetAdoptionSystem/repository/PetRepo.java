package com.example.PetAdoptionSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PetAdoptionSystem.model.Pet;

@Repository
public interface PetRepo extends JpaRepository<Pet,Integer> {

    
}
