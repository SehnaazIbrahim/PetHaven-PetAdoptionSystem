package com.example.PetAdoptionSystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PetAdoptionSystem.model.User;
import com.example.PetAdoptionSystem.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(int id) {
        return userRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }

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

    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }
}
