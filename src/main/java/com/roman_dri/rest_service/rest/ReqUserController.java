package com.roman_dri.rest_service.rest;

import com.roman_dri.rest_service.dto.ReqCreateDto;
import com.roman_dri.rest_service.dto.ReqDto;
import com.roman_dri.rest_service.dto.ReqUpdateDto;
import com.roman_dri.rest_service.service.ReqUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users/{userId}/requests")
@Tag(name = "Operations with requests available to the user.")
public class ReqUserController {

    private final ReqUserService reqUserService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create a new request.")
    public ReqDto createRequest(@PathVariable Long userId, @Valid @RequestBody ReqCreateDto reqCreateDto) {
        return reqUserService.createRequest(userId, reqCreateDto);
    }

    @PatchMapping("/{reqId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Edit the request in the \"draft\" status.")
    public ReqDto updateRequestByUser(@PathVariable Long userId, @PathVariable Long reqId,
                                      @Valid @RequestBody ReqUpdateDto reqUpdateDto) {
        return reqUserService.updateRequestByUser(userId, reqId, reqUpdateDto);
    }

    @PatchMapping("/{reqId}/sent")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Send requests for consideration to the operator.")
    public ReqDto sentRequestForConsideration(@PathVariable Long reqId, @PathVariable Long userId) {
        return reqUserService.sentRequestForConsideration(reqId, userId);
    }

    @GetMapping("/new")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "View created requests with date sorting from old to new.")
    List<ReqDto> getAllRequestsByUserIdWithSortFromOldToNew(@PathVariable Long userId,
                                                            @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                            @Positive @RequestParam(defaultValue = "5") Integer size) {
        return reqUserService.getAllRequestsByUserIdWithSortFromOldToNew(userId, from, size);
    }

    @GetMapping("/old")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "View created requests with date sorting from new to old.")
    List<ReqDto> getAllRequestsByUserIdWithSortFromNewToOld(@PathVariable Long userId,
                                                            @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                            @Positive @RequestParam(defaultValue = "5") Integer size) {
        return reqUserService.getAllRequestsByUserIdWithSortFromNewToOld(userId, from, size);
    }

    @DeleteMapping("/{reqId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a request.")
    public void userDeleteRequest(@PathVariable Long reqId, @PathVariable Long userId) {
        reqUserService.userDeleteRequest(reqId, userId);
    }
}
