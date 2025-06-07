package com.medisync.authentication.service;

import com.medisync.authentication.entity.User;
import com.medisync.authentication.repository.UserRepository;
import com.medisync.authentication.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository repository, JwtUtil jwtUtil, BCryptPasswordEncoder passwordEncoder){
        this.repository = repository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }


    // FIXME right now we can create unstop user with same details (what is the diff key, id generate auto)
    // TODO check if try to create user that already exist
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = repository.save(user);
        log.info("create new user with id " + savedUser.getId());
        savedUser.setPassword(null);
        return savedUser;
    }

    public List<User> getAll() {
        List<User> users = repository.findAll();
        users.forEach(user -> user.setPassword(null));
        return users;
    }

    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    public User update(User user, Long id) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        if (user.getEmail() != null) existingUser.setEmail(user.getEmail());
        if (user.getRole() != null) existingUser.setRole(user.getRole());

        User updatedUser = repository.save(existingUser);
        log.info("updated user with id: " + id);
        updatedUser.setPassword(null);
        return updatedUser;
    }

    public void delete(Long id) {
        repository.deleteById(id);
        log.info("user with id " + id + " deleted");
    }

    public String authenticateAndGenerateToken(String email, String password){
        Optional<User> userOptional = repository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getId(), user.getRole().name());

    }



}

