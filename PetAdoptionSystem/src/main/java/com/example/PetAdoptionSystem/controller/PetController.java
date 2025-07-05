package com.example.PetAdoptionSystem.controller;

import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.model.User;
import com.example.PetAdoptionSystem.model.AdoptionRequest;
import com.example.PetAdoptionSystem.service.PetService;
import com.example.PetAdoptionSystem.service.UserService;
import com.example.PetAdoptionSystem.service.AdoptionRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petservice;

    @Autowired
    private UserService userService;

    @Autowired
    private AdoptionRequestService adoptionRequestService;

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

    // ✅ ADOPT PET ENDPOINT
    @PostMapping("/adopt/{petId}")
    public ResponseEntity<String> adoptPet(
            @PathVariable int petId,
            @RequestParam String userEmail) {

        Pet pet = petservice.getPetById(petId);

        if (pet.getStatus().equalsIgnoreCase("Adopted")) {
            return ResponseEntity.badRequest().body("This pet is already adopted.");
        }

        User user = userService.getUserByEmail(userEmail);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        AdoptionRequest request = new AdoptionRequest();
        request.setPet(pet);
        request.setUser(user);
        request.setStatus("Pending");
        request.setRequestDate(java.time.LocalDate.now());

        adoptionRequestService.createAdoptionRequest(request);

        pet.setStatus("Adopted");
        petservice.updatePet(pet, petId);

        return ResponseEntity.ok("Adoption request submitted successfully.");
    }
}
