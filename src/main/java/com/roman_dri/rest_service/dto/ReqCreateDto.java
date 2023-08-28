package com.roman_dri.rest_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReqCreateDto {

    @NotBlank(message = "'name' can not be blank")
    @Schema(example = "user request number 1")
    private String name;

    @Size(min = 10, max = 1000)
    @NotBlank(message = "'user_message' can not be blank")
    @Schema(example = "i need help")
    private String userMessage;

    @Positive
    @Schema(example = "1")
    private Long user;
}
