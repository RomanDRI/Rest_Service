package com.roman_dri.rest_service.dto;

import com.roman_dri.rest_service.entity.ReqState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReqUpdateDto {

    private Long id;

    @NotBlank(message = "'name' can not be blank")
    @Schema(example = "user request number 1")
    private String name;

    @Size(min = 10, max = 1000)
    @NotBlank(message = "'user_message' can not be blank")
    @Schema(example = "i need help")
    private String userMessage;

    @Schema(example = "DRAFT")
    private ReqState state;
}
