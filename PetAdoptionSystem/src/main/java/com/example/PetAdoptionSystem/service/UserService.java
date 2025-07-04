package com.example.PetAdoptionSystem.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PetAdoptionSystem.model.User;
import com.example.PetAdoptionSystem.model.Pet;
import com.example.PetAdoptionSystem.model.AdoptionRequest;
import com.example.PetAdoptionSystem.repository.UserRepo;
import com.example.PetAdoptionSystem.repository.PetRepo;
import com.example.PetAdoptionSystem.repository.AdoptionRequestRepo;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PetRepo petRepository;

    @Autowired
    private AdoptionRequestRepo adoptionRequestRepository;

    // ✅ Automatically create 1 admin when app starts
    @PostConstruct
    public void initAdmin() {
        Optional<User> admin = userRepo.findByEmail("admin@example.com");
        if (admin.isEmpty()) {
            User newAdmin = new User();
            newAdmin.setName("Admin");
            newAdmin.setEmail("admin@example.com");
            newAdmin.setPassword("admin123");
            newAdmin.setContact("0000000000");
            newAdmin.setRole("ADMIN");
            userRepo.save(newAdmin);
        }
    }

    // ✅ Signup as USER
    public User signup(User user) {
        user.setRole("USER"); // forcefully set role to USER
        return userRepo.save(user);
    }

    // ✅ Login (returns full user with role)
    public User login(String email, String password) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    // ✅ Get all users
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // ✅ Get by ID
    public User getUserById(int id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

 // ✅ Get user profile with adopted pets (for dashboard)
public Map<String, Object> getUserProfileWithPets(int id) {
    User user = userRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

    List<AdoptionRequest> requests = adoptionRequestRepository.findByUserId(id);

    List<Pet> adoptedPets = requests.stream()
            .map(AdoptionRequest::getPet)  // ✅ use the relationship directly
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

    Map<String, Object> response = new HashMap<>();
    response.put("id", user.getId());
    response.put("name", user.getName());
    response.put("email", user.getEmail());
    response.put("adoptedPets", adoptedPets);

    return response;
}


    // ✅ Update user (admin only)
    public User updateUser(int id, User updatedUser) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setContact(updatedUser.getContact());
        existingUser.setRole(updatedUser.getRole());

        return userRepo.save(existingUser);
    }

    // ✅ Delete user (admin only)
    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }
}

