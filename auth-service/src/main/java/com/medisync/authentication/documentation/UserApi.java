package com.medisync.authentication.documentation;

import com.medisync.authentication.dto.request.LoginRequestDto;
import com.medisync.authentication.dto.request.create.UserCreateDto;
import com.medisync.authentication.dto.response.LoginResponseDto;
import com.medisync.authentication.dto.response.UserResponseDto;
import com.medisync.authentication.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@Tag(name = "User Management", description = "APIs for managing users including registration and authentication")
public interface UserApi {

    @Operation(summary = "Register a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    UserResponseDto create(UserCreateDto dto);

    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "Users found")
    List<UserResponseDto> getAll();

    @Operation(summary = "Get user by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    ResponseEntity<Optional<User>> getById(Long id);

    @Operation(summary = "Update user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    ResponseEntity<User> update(Long id, User user);

    @Operation(summary = "Delete user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    ResponseEntity<String> delete(Long id);

    @Operation(summary = "User login")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful",
                    content = @Content(schema = @Schema(implementation = LoginResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    ResponseEntity<?> login(LoginRequestDto loginRequest);
}