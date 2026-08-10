package com.ecommerce.engine.controller;

import com.ecommerce.engine.model.User;
import com.ecommerce.engine.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for user management.
 * <p>
 * {@code @RestController} = {@code @Controller} + {@code @ResponseBody}.
 * Return values are serialized to JSON automatically by Jackson.
 * <p>
 * Base path: {@code /api/users}
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /** GET /api/users — list all registered users */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    /** GET /api/users/{id} — fetch one user by primary key */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    /** POST /api/users — register a new user (JSON body → User entity) */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /** PUT /api/users/{id} — update name, email, or address */
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    /** DELETE /api/users/{id} — remove a user record */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
