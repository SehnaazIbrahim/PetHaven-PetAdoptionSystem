package com.example.PetAdoptionSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PetAdoptionSystem.model.AdoptionRequest;
import com.example.PetAdoptionSystem.repository.AdoptionRequestRepo;

@Service
public class AdoptionRequestService {

    @Autowired
    AdoptionRequestRepo adoptionRequestRepo;

    public List<AdoptionRequest> getAllAdoptionRequests() {
        return adoptionRequestRepo.findAll();
    }

    public AdoptionRequest getAdoptionRequestById(int id) {
        return adoptionRequestRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Adoption Request not found with id: " + id));
    }

    public AdoptionRequest createAdoptionRequest(AdoptionRequest request) {
        return adoptionRequestRepo.save(request);
    }

    public AdoptionRequest updateAdoptionRequestStatus(int id, String newStatus) {
        AdoptionRequest existingRequest = adoptionRequestRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Adoption Request not found with id: " + id));

        existingRequest.setStatus(newStatus);
        return adoptionRequestRepo.save(existingRequest);
    }

    public void deleteAdoptionRequest(int id) {
        adoptionRequestRepo.deleteById(id);
    }

    public List<AdoptionRequest> getRequestsByUserEmail(String email) {
    return adoptionRequestRepo.findByUserEmail(email);
}



}
