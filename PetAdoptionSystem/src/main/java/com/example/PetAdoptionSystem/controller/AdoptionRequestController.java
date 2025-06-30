package com.example.PetAdoptionSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.PetAdoptionSystem.model.AdoptionRequest;
import com.example.PetAdoptionSystem.service.AdoptionRequestService;

@RestController
public class AdoptionRequestController {

    @Autowired
    AdoptionRequestService adoptionRequestService;

    @GetMapping
    public List<AdoptionRequest> getAllAdoptionRequests() {
        return adoptionRequestService.getAllAdoptionRequests();
    }

    @GetMapping("/adoptions/{id}")
    public AdoptionRequest getAdoptionRequestById(@PathVariable int id) {
        return adoptionRequestService.getAdoptionRequestById(id);
    }

    @PostMapping("/adoptions")
    public AdoptionRequest createAdoptionRequest(@RequestBody AdoptionRequest request) {
        return adoptionRequestService.createAdoptionRequest(request);
    }

    @PutMapping("/adoptions/{id}")
    public AdoptionRequest updateAdoptionRequestStatus(@PathVariable int id, @RequestBody AdoptionRequest updatedRequest) {
        return adoptionRequestService.updateAdoptionRequestStatus(id, updatedRequest.getStatus());
    }

    @DeleteMapping("/adoptions/{id}")
    public String deleteAdoptionRequest(@PathVariable int id) {
        adoptionRequestService.deleteAdoptionRequest(id);
        return "Adoption Request deleted successfully with id: " + id;
    }
}
