package com.shei.cms.controller;

import com.shei.cms.entity.User;
import com.shei.cms.repository.UserRepository;
import com.shei.cms.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

        import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")

public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        // Check if username already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }

        // Encode the password and save the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    // Login an existing user
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser.isPresent() && passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            String token = jwtUtils.generateToken(existingUser.get().getUsername(), Boolean.parseBoolean(existingUser.get().getRole()));


            // Prepare response data
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("token", token);
            responseData.put("user", existingUser.get());

            return ResponseEntity.ok(responseData);
        }

        return ResponseEntity.badRequest().body("Invalid credentials");
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }

        return ResponseEntity.notFound().build(); // Returns 404 if user not found
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll(); // Retrieve all users from the database

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build(); // Returns 204 No Content if no users found
        }

        return ResponseEntity.ok(users); // Return all users in the response
    }
}



