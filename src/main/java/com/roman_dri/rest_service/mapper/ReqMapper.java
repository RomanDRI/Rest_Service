package com.roman_dri.rest_service.mapper;

import com.roman_dri.rest_service.dto.ReqCreateDto;
import com.roman_dri.rest_service.dto.ReqDto;
import com.roman_dri.rest_service.dto.ReqUpdateDto;
import com.roman_dri.rest_service.entity.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ReqMapper {

    @Mapping(target = "user.id", source = "user")
    Request toRequest(ReqCreateDto reqCreateDto);

    ReqDto toReqDto(Request request);

    Request toReq(ReqUpdateDto reqUpdateDto);
}
