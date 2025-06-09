package com.medisync.authentication.unit.controller;


import com.medisync.authentication.controller.UserController;
import com.medisync.authentication.dto.request.create.UserCreateDto;
import com.medisync.authentication.dto.response.UserResponseDto;
import com.medisync.authentication.entity.User;
import com.medisync.authentication.enums.UserType;
import com.medisync.authentication.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


    @ExtendWith(MockitoExtension.class)
    class UserControllerTest {
        private static final Logger log = LoggerFactory.getLogger(UserControllerTest.class);

        @Mock
        private UserService userService;

        @InjectMocks
        private UserController userController;

        @Test
        void shouldCreateUserSuccessfully() {
            log.info("Starting test: shouldCreateUserSuccessfully");
            UserCreateDto createDto = new UserCreateDto("test@test.com", "password", "12345", UserType.PATIENT);
            User expectedUser = User.builder()
                    .email("test@test.com")
                    .password("password")
                    .role(UserType.PATIENT)
                    .identityNumber("12345")
                    .build();
            log.debug("Mocking userService.create with expectedUser: {}", expectedUser);
            when(userService.create(any(User.class))).thenReturn(expectedUser);

            log.debug("Executing create user operation");
            UserResponseDto response = userController.create(createDto);

            verify(userService).create(any(User.class));
            assertThat(response).isNotNull();
            log.info("Completed test: shouldCreateUserSuccessfully");
        }

        @Test
        void shouldReturnAllUsers() {
            log.info("Starting test: shouldReturnAllUsers");
            List<User> users = Arrays.asList(
                    User.builder()
                            .id(1L)
                            .email("user1@test.com")
                            .password("pass1")
                            .role(UserType.DOCTOR)
                            .identityNumber("12345")
                            .build(),
                    User.builder()
                            .id(2L)
                            .email("user2@test.com")
                            .password("pass2")
                            .role(UserType.PATIENT)
                            .identityNumber("67890")
                            .build()
            );
            log.debug("Mocking userService.getAll with {} users", users.size());
            when(userService.getAll()).thenReturn(users);

            log.debug("Executing getAll users operation");
            List<UserResponseDto> response = userController.getAll();

            verify(userService).getAll();
            assertThat(response).hasSize(2);
            log.info("Completed test: shouldReturnAllUsers");
        }

        @Test
        void shouldReturnUserByIdWhenExists() {
            log.info("Starting test: shouldReturnUserByIdWhenExists");
            User user = User.builder()
                    .id(1L)
                    .email("test@test.com")
                    .password("password")
                    .role(UserType.ADMIN)
                    .identityNumber("12345")
                    .build();
            log.debug("Mocking userService.getById with user: {}", user);
            when(userService.getById(1L)).thenReturn(Optional.of(user));

            log.debug("Executing getById operation for id: {}", 1L);
            ResponseEntity<Optional<User>> response = userController.getById(1L);

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isPresent();
            assertThat(response.getBody().get().getPassword()).isEmpty();
            log.info("Completed test: shouldReturnUserByIdWhenExists");
        }

        @Test
        void shouldUpdateUserSuccessfully() {
            log.info("Starting test: shouldUpdateUserSuccessfully");
            User user = User.builder()
                    .id(1L)
                    .email("updated@test.com")
                    .password("newpass")
                    .role(UserType.DOCTOR)
                    .identityNumber("54321")
                    .build();
            log.debug("Mocking userService.update with user: {}", user);
            when(userService.update(any(User.class), eq(1L))).thenReturn(user);

            log.debug("Executing update operation for id: {}", 1L);
            ResponseEntity<User> response = userController.update(1L, user);

            verify(userService).update(user, 1L);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isEqualTo(user);
            log.info("Completed test: shouldUpdateUserSuccessfully");
        }
    }


