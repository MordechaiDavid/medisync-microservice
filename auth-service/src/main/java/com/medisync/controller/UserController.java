package com.medisync.controller;

import com.medisync.dto.request.LoginRequestDto;
import com.medisync.dto.request.create.UserCreateDto;
import com.medisync.dto.response.LoginResponseDto;
import com.medisync.dto.response.UserResponseDto;
import com.medisync.entity.User;
import com.medisync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto create(@RequestBody UserCreateDto dto) {
        User user = service.create(User.fromDto(dto));
        return UserResponseDto.fromUser(user);
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        List<User> users = service.getAll();
        return users.stream().map(UserResponseDto::fromUser).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable Long id) {
        Optional<User> optionalUser = service.getById(id);
        optionalUser.ifPresent(user -> user.setPassword(""));
        return new ResponseEntity<Optional<User>>(optionalUser, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        return new ResponseEntity<User>(service.update(user, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<String>("User deleted successfully with id: " + id, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        String token = service.authenticateAndGenerateToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        if (token != null) {
            return ResponseEntity.ok(new LoginResponseDto(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }



}

