package com.medisync.authentication.dto.response;

import com.medisync.authentication.entity.User;
import lombok.*;

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

