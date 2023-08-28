package com.roman_dri.rest_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.roman_dri.rest_service.entity.ReqState;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReqDto {

    private Long id;

    private String name;

    private String userMessage;

    private UserShortDto user;

    private ReqState state;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;
}
