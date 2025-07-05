package com.example.PetAdoptionSystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.PetAdoptionSystem.model.AdoptionRequest;
import com.example.PetAdoptionSystem.service.AdoptionRequestService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/adoptions")
public class AdoptionRequestController {

    @Autowired
    private AdoptionRequestService adoptionRequestService;

    @GetMapping
    public List<AdoptionRequest> getAllAdoptionRequests() {
        return adoptionRequestService.getAllAdoptionRequests();
    }

    @GetMapping("/{id}")
    public AdoptionRequest getAdoptionRequestById(@PathVariable int id) {
        return adoptionRequestService.getAdoptionRequestById(id);
    }

    @PostMapping
    public AdoptionRequest createAdoptionRequest(@RequestBody AdoptionRequest request) {
        return adoptionRequestService.createAdoptionRequest(request);
    }

    @PutMapping("/{id}")
    public AdoptionRequest updateAdoptionRequestStatus(@PathVariable int id,
            @RequestBody AdoptionRequest updatedRequest) {
        return adoptionRequestService.updateAdoptionRequestStatus(id, updatedRequest.getStatus());
    }

    @DeleteMapping("/{id}")
    public String deleteAdoptionRequest(@PathVariable int id) {
        adoptionRequestService.deleteAdoptionRequest(id);
        return "Adoption Request deleted successfully with id: " + id;
    }

    @GetMapping("/user/requests")
public List<Map<String, Object>> getRequestsByUser(@RequestParam String email) {
    List<AdoptionRequest> requests = adoptionRequestService.getRequestsByUserEmail(email);

    List<Map<String, Object>> response = new ArrayList<>();
    for (AdoptionRequest req : requests) {
        Map<String, Object> map = new HashMap<>();
        map.put("petName", req.getPet().getName());
        map.put("requestedDate", req.getRequestDate());
        map.put("status", req.getStatus());
        response.add(map);
    }

    return response;
}

}
