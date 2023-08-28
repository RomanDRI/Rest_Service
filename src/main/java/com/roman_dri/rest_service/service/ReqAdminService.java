package com.roman_dri.rest_service.service;



import com.roman_dri.rest_service.dto.UserDto;

import java.util.List;

public interface ReqAdminService {

    List<UserDto> getAllUsers(Integer from, Integer size);

    UserDto searchUserByName(String name);

    UserDto assignOperator(Long userId);
}
