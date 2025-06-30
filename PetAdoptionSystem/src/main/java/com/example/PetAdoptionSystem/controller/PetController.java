package com.example.PetAdoptionSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.service.*;

@RestController
public class PetController {

    @Autowired
    PetService petservice;

    @GetMapping("/pets")
    public List<Pet> getAllPets() {
        return petservice.getAllPets();
    }

    @PostMapping("/addpet")
    public String addNewPet(@RequestBody Pet pet) {
        petservice.addNewPet(pet);
        return "Pet Added Successfully";
    }
    
    @GetMapping("/pets/{id}")
    public Pet getPetById(@PathVariable("id") int id ){
            return petservice.getPetById(id);

    }

   @PutMapping("/pets/{id}")
   public Pet updatePet(@RequestBody Pet updatedPet, @PathVariable("id") int id){
     return petservice.updatePet(updatedPet,id);
   }


   @DeleteMapping("/deletepet/{id}")
   public String deletePetById(@PathVariable("id") int id){

        petservice.deletePetById(id);
        return "Pet :"+id+ " Deleted Sucessfully ";
       }


}
