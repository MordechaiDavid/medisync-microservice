package com.medisync.authentication.dto.request.create;

import com.medisync.authentication.enums.UserType;
import lombok.*;

@Data
public class UserCreateDto {
    private String identityNumber;
    private String email;
    private String password;
    private UserType role;

}
