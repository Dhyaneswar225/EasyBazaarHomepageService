package com.dazzledepot.homepage_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    @GetMapping
    public ResponseEntity<Map<String, String>> getProfile(@RequestParam String email) {
        // Hypothetical logic to fetch user data (e.g., from a UserRepository)
        Map<String, String> profile = new HashMap<>();
        profile.put("email", email);
        profile.put("name", "User " + email.split("@")[0]); // Example name
        return ResponseEntity.ok(profile);
    }
}