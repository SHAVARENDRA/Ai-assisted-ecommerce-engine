package com.ecommerce.engine.service;

import com.ecommerce.engine.model.User;
import com.ecommerce.engine.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Business logic for user registration and lookup.
 * <p>
 * Controllers delegate here; this layer validates rules before calling the Repository.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
    }

    /**
     * Registers a user. Rejects duplicate emails before hitting the database constraint.
     */
    @Transactional
    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already registered: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    @Transactional
    public User update(Long id, User updated) {
        User existing = findById(id);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setAddress(updated.getAddress());
        return userRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.delete(findById(id));
    }
}
