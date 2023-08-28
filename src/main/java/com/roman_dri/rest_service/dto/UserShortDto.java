package com.roman_dri.rest_service.dto;

import com.roman_dri.rest_service.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserShortDto {

    @NotBlank(message = "'name' can not be blank")
    private String name;

    @NotNull
    private UserRole role;
}
