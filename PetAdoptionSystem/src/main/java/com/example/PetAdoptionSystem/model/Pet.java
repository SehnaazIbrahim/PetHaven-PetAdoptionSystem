package com.example.PetAdoptionSystem.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String type;
    private String breed;
    private int age;
    private String description;
    private String status;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY)
    private List<AdoptionRequest> requests;

    public Pet() {}

    public Pet(String name, String type, String breed, int age, String description, String status, String imageUrl) {
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.age = age;
        this.description = description;
        this.status = status;
        this.imageUrl = imageUrl;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public List<AdoptionRequest> getRequests() { return requests; }
    public void setRequests(List<AdoptionRequest> requests) { this.requests = requests; }
}

