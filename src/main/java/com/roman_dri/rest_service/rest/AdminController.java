package com.roman_dri.rest_service.rest;

import com.roman_dri.rest_service.dto.UserDto;
import com.roman_dri.rest_service.service.ReqAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/users")
@Tag(name = "Operations with requests available to the administrator.")
public class AdminController {

    private final ReqAdminService reqAdminService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "View the list of users.")
    public List<UserDto> getAllUsers(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                     @Positive @RequestParam(defaultValue = "10") Integer size) {
        return reqAdminService.getAllUsers(from, size);
    }

    @GetMapping("/search")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Search for a specific user by his name/part of the name.")
    UserDto searchUserByName(@RequestParam String name) {
        return reqAdminService.searchUserByName(name);
    }

    @PatchMapping("/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Assign operator rights to user.")
    public UserDto assignOperator(@PathVariable Long userId) {
        return reqAdminService.assignOperator(userId);
    }
}
