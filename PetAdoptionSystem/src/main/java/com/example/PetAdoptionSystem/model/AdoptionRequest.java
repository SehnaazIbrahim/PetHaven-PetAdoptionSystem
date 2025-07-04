package com.example.PetAdoptionSystem.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class AdoptionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate requestDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public AdoptionRequest() {}

    public AdoptionRequest(LocalDate requestDate, String status, User user, Pet pet) {
        this.requestDate = requestDate;
        this.status = status;
        this.user = user;
        this.pet = pet;
    }

    // ✅ Only use mapped fields
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDate requestDate) { this.requestDate = requestDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Pet getPet() { return pet; }
    public void setPet(Pet pet) { this.pet = pet; }
}
