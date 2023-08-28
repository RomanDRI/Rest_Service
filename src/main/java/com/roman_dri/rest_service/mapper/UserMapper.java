package com.roman_dri.rest_service.mapper;

import com.roman_dri.rest_service.dto.UserDto;
import com.roman_dri.rest_service.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);
}
