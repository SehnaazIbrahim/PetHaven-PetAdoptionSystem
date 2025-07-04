package com.example.PetAdoptionSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.service.PetService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petservice;

    @GetMapping
    public List<Pet> getAllPets() {
        return petservice.getAllPets();
    }

    @PostMapping
    public String addNewPet(@RequestBody Pet pet) {
        petservice.addNewPet(pet);
        return "Pet added successfully";
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable int id) {
        return petservice.getPetById(id);
    }

    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable int id, @RequestBody Pet updatedPet) {
        return petservice.updatePet(updatedPet, id);
    }

    @DeleteMapping("/{id}")
    public String deletePetById(@PathVariable int id) {
        petservice.deletePetById(id);
        return "Pet deleted successfully with id: " + id;
    }
}
