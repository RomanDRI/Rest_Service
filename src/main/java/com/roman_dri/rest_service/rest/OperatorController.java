package com.roman_dri.rest_service.rest;

import com.roman_dri.rest_service.dto.ReqDto;
import com.roman_dri.rest_service.service.ReqOperatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/operator/requests")
@Tag(name = "Operations with requests available to the operator.")
public class OperatorController {

    private final ReqOperatorService reqOperatorService;

    @PatchMapping("/{reqId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Process the user's request.")
    public ReqDto processingRequestByOperator(@PathVariable Long reqId, @RequestParam Boolean approved) {
        return reqOperatorService.processingRequestByOperator(reqId, approved);
    }

    @GetMapping("/search/new")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "View submitted requests only for a specific user by his name/part of the name " +
            "with date sorting from old to new.")
    List<ReqDto> getAllSentRequestsByUserNameWithSortFromOldToNew(@RequestParam String name,
                                                                  @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                  @Positive @RequestParam(defaultValue = "5") Integer size) {
        return reqOperatorService.getAllSentRequestsByUserNameWithSortFromOldToNew(name, from, size);
    }

    @GetMapping("/search/old")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "View submitted requests only for a specific user by his name/part of the name " +
            "with date sorting from new to old.")
    List<ReqDto> getAllSentRequestsByUserNameWithSortFromNewToOld(@RequestParam String name,
                                                                  @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                  @Positive @RequestParam(defaultValue = "5") Integer size) {
        return reqOperatorService.getAllSentRequestsByUserNameWithSortFromNewToOld(name, from, size);
    }

    @GetMapping("/all/new")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "View all requests submitted for consideration with date sorting from old to new.")
    List<ReqDto> getAllSentRequestsWithSortFromOldToNew(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                        @Positive @RequestParam(defaultValue = "5") Integer size) {
        return reqOperatorService.getAllSentRequestsWithSortFromOldToNew(from, size);
    }

    @GetMapping("/all/old")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "View all requests submitted for consideration with date sorting from new to old.")
    List<ReqDto> getAllSentRequestsWithSortFromNewToOld(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                        @Positive @RequestParam(defaultValue = "5") Integer size) {
        return reqOperatorService.getAllSentRequestsWithSortFromNewToOld(from, size);
    }
}
