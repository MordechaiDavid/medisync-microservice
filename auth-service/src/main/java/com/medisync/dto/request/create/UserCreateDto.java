package com.medisync.dto.request.create;

import com.medisync.enums.UserType;
import lombok.*;
import java.time.LocalDate;

@Data
public class UserCreateDto {
    private String identityNumber;
    private String email;
    private String password;
    private UserType role;

}
