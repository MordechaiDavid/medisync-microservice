package com.medisync.authentication.dto.request.update;

import com.medisync.authentication.enums.UserType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDto {
    private Long id;
    private String name;
    private String email;
    private UserType role;
}
