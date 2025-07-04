package com.example.PetAdoptionSystem.controller;

import com.example.PetAdoptionSystem.model.User;
import com.example.PetAdoptionSystem.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    
    @GetMapping("/profile/{id}")
    public ResponseEntity<Map<String, Object>> getUserProfile(@PathVariable int id) {
      Map<String, Object> profile = userService.getUserProfileWithPets(id);
        return ResponseEntity.ok(profile);
    }


    // ✅ Signup (role is auto USER)
    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userService.signup(user);
    }

    // ✅ Login for USER/ADMIN
    @PostMapping("/login")
    public User login(@RequestBody User loginUser) {
        return userService.login(loginUser.getEmail(), loginUser.getPassword());
    }

    // ✅ Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // ✅ Get user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    // ✅ Update user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // ✅ Delete user
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "User deleted successfully with id: " + id;
    }

    
}
