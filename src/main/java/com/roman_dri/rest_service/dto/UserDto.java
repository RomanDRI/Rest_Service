package com.roman_dri.rest_service.dto;

import com.roman_dri.rest_service.entity.UserRole;
import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "'name' can not be blank")
    private String name;

    @Email
    @NotBlank(message = "'email' can not be blank")
    private String email;

    @NotNull
    private UserRole role;
}
