package com.roman_dri.rest_service.service;



import com.roman_dri.rest_service.dto.ReqCreateDto;
import com.roman_dri.rest_service.dto.ReqDto;
import com.roman_dri.rest_service.dto.ReqUpdateDto;

import java.util.List;

public interface ReqUserService {

    ReqDto createRequest(Long userId, ReqCreateDto reqCreateDto);

    ReqDto updateRequestByUser(Long userId, Long reqId, ReqUpdateDto reqUpdateDto);

    List<ReqDto> getAllRequestsByUserIdWithSortFromNewToOld(Long userId, Integer from, Integer size);

    List<ReqDto> getAllRequestsByUserIdWithSortFromOldToNew(Long userId, Integer from, Integer size);

    ReqDto sentRequestForConsideration(Long reqId, Long userId);

    void userDeleteRequest(Long reqId, Long userId);
}

