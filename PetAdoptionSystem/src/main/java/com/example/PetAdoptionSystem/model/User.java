package com.example.PetAdoptionSystem.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String password;
    private String contact;
    private String role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<AdoptionRequest> requests;

    public User() {}

    public User(int id, String name, String email, String password, String contact, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.role = role;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public List<AdoptionRequest> getRequests() { return requests; }
    public void setRequests(List<AdoptionRequest> requests) { this.requests = requests; }
}
