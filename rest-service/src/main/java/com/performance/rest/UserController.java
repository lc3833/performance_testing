package com.performance.rest;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/small")
    public Map<String, Object> getSmall() {
        Map<String, Object> user = new HashMap<>();
        user.put("id", 1);
        user.put("name", "John Doe");
        user.put("email", "john@example.com");
        user.put("age", 30);
        return user;
    }

    @GetMapping("/medium")
    public List<Map<String, Object>> getMedium() {
        List<Map<String, Object>> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Map<String, Object> user = new HashMap<>();
            user.put("id", i);
            user.put("name", "User " + i);
            user.put("email", "user" + i + "@example.com");
            user.put("age", 20 + i % 40);
            users.add(user);
        }
        return users;
    }

    @GetMapping("/large")
    public List<Map<String, Object>> getLarge() {
        List<Map<String, Object>> users = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Map<String, Object> user = new HashMap<>();
            user.put("id", i);
            user.put("name", "User " + i);
            user.put("email", "user" + i + "@example.com");
            user.put("age", 20 + i % 40);
            user.put("address", "Street " + i);
            user.put("phone", "06" + i);
            user.put("company", "Company " + i % 50);
            users.add(user);
        }
        return users;
    }
}