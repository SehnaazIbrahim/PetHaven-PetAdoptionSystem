package com.example.PetAdoptionSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PetAdoptionSystem.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>  {

    Optional<User> findByEmailAndPassword(String email, String password);  // 🔑 for login

    Optional<User> findByEmail(String email);  // ✅ for checking if email already exists (e.g. admin init, signup check)
    

}
