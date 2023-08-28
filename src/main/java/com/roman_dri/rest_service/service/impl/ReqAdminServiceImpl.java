package com.roman_dri.rest_service.service.impl;

import com.roman_dri.rest_service.dto.UserDto;
import com.roman_dri.rest_service.error.exception.NotFoundException;
import com.roman_dri.rest_service.mapper.UserMapper;
import com.roman_dri.rest_service.entity.User;
import com.roman_dri.rest_service.entity.UserRole;
import com.roman_dri.rest_service.repository.UserRepository;
import com.roman_dri.rest_service.service.ReqAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReqAdminServiceImpl implements ReqAdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers(Integer from, Integer size) {

        Pageable pageable = PageRequest.of(from, size);
        log.info("Received a list of all users with size of {}.", size);
        return userRepository.findAll(pageable).stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto searchUserByName(String name) {
        String query = name.toLowerCase();

        User user = userRepository.findUserByName(query);
        if (user.getName().isEmpty()) {
            throw new NotFoundException("User not found.");
        }
        log.info("Received a user with name {}.", user.getName());
        return userMapper.toUserDto(user);
    }

    @Override
    @Transactional
    public UserDto assignOperator(Long userId) {
        User user = getUser(userId);

        if (user.getRole().equals(UserRole.USER)) {
            user.setRole(UserRole.OPERATOR);
        }
        log.info("Assigned operator rights to the user with id {}.", userId);
        return userMapper.toUserDto(userRepository.save(user));
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(String.format("User with id=%d not found", userId)));
    }
}
