package com.example.PetAdoptionSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.repository.PetRepo;

@Service
public class PetService {

    @Autowired
    PetRepo petrepo;

    public List<Pet> getAllPets() {
        return petrepo.findAll();
    }

    public void addNewPet(Pet pet) {
        petrepo.save(pet);
    }

    public Pet getPetById(int id) {
        return petrepo.findById(id).orElseThrow(() -> new RuntimeException("Pet not found with id: " + id));
    }

    public Pet updatePet(Pet updatedPet, int id) {
        Pet pet = petrepo.findById(id).orElseThrow(() -> new RuntimeException("Pet not found with id: " + id));
        pet.setAge(updatedPet.getAge());
        pet.setName(updatedPet.getName());
        pet.setBreed(updatedPet.getBreed());
        pet.setDescription(updatedPet.getDescription());
        pet.setStatus(updatedPet.getStatus());
        pet.setImageUrl(updatedPet.getImageUrl());
        pet.setType(updatedPet.getType());
        return petrepo.save(pet);
    }

    public void deletePetById(int id) {
        petrepo.deleteById(id);
    }

}