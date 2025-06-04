package com.medisync.dto.response;

import com.medisync.entity.User;
import com.medisync.enums.UserType;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String identityNumber;
    private String email;

    public static UserResponseDto fromUser(User user){
        return UserResponseDto.builder()
                .email(user.getEmail())
                .id(user.getId())
                .identityNumber(user.getIdentityNumber())
                .build();
    }
}

