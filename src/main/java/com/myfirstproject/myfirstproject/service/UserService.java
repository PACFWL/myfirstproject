package com.myfirstproject.myfirstproject.service;

import com.myfirstproject.myfirstproject.model.User;
import com.myfirstproject.myfirstproject.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.security.Key;


import java.util.Date;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User updateUser(String id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public String loginUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
            return generateToken(existingUser.get());
        }
        throw new RuntimeException("Invalid email or password");
    }

    private String generateToken(User user) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Gera uma chave segura automaticamente(aleatória)

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Token válido para 1 dia
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


}
