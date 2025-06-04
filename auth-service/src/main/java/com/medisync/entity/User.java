package com.medisync.entity;

import com.medisync.dto.request.create.UserCreateDto;
import com.medisync.dto.request.update.UserUpdateDto;
import com.medisync.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String identityNumber;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType role;

    @CreationTimestamp
    private LocalDateTime createdAt;


    public static User fromDto(UserCreateDto dto){
        return User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(dto.getRole())
                .identityNumber(dto.getIdentityNumber())
                .build();
    }
    public static User fromUserDto(UserUpdateDto dto){
        return User.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .role(dto.getRole())
                .build();
    }


}

