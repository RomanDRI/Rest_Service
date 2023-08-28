package com.roman_dri.rest_service.service.impl;

import com.roman_dri.rest_service.dto.ReqDto;
import com.roman_dri.rest_service.error.exception.ConflictException;
import com.roman_dri.rest_service.error.exception.NotFoundException;
import com.roman_dri.rest_service.mapper.ReqMapper;
import com.roman_dri.rest_service.entity.ReqState;
import com.roman_dri.rest_service.entity.Request;
import com.roman_dri.rest_service.entity.User;
import com.roman_dri.rest_service.repository.ReqRepository;
import com.roman_dri.rest_service.repository.UserRepository;
import com.roman_dri.rest_service.service.ReqOperatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReqOperatorServiceImpl implements ReqOperatorService {

    private final ReqRepository reqRepository;
    private final UserRepository userRepository;
    private final ReqMapper reqMapper;

    @Override
    @Transactional
    public ReqDto processingRequestByOperator(Long reqId, Boolean approved) {
        Request request = getRequest(reqId);

        if (!request.getState().equals(ReqState.SENT)) {
            throw new ConflictException("Only submitted requests can be processed.");
        }
        if (approved) {
            request.setState(ReqState.ACCEPTED);
        } else {
            request.setState(ReqState.REJECTED);
        }

        log.info("Updated request with id {}.", reqId);
        return reqMapper.toReqDto(reqRepository.save(request));
    }

    @Override
    public List<ReqDto> getAllSentRequestsByUserNameWithSortFromNewToOld(String name, Integer from, Integer size) {
        String query = name.toLowerCase();

        User user = userRepository.findUserByName(query);
        if (user.getName().isEmpty()) {
            throw new NotFoundException("User not found.");
        }

        Page<Request> requests = reqRepository.findAllByUserIdOrderByCreatedOnDesc(user.getId(), PageRequest.of(from / size, size));

        log.info("Received a list of all-user with id {} sent requests sorted from newer to older.", user.getId());
        return requests.stream()
                .map(reqMapper::toReqDto)
                .filter(reqDto -> reqDto.getState().equals(ReqState.SENT))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReqDto> getAllSentRequestsByUserNameWithSortFromOldToNew(String name, Integer from, Integer size) {
        String query = name.toLowerCase();

        User user = userRepository.findUserByName(query);
        if (user.getName().isEmpty()) {
            throw new NotFoundException("User not found.");
        }

        Page<Request> requests = reqRepository.findAllByUserIdOrderByCreatedOnAsc(user.getId(), PageRequest.of(from / size, size));

        log.info("Received a list of all-user with id {} sent requests sorted from older to newer.", user.getId());
        return requests.stream()
                .map(reqMapper::toReqDto)
                .filter(reqDto -> reqDto.getState().equals(ReqState.SENT))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReqDto> getAllSentRequestsWithSortFromNewToOld(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from, size);

        log.info("Received a list of all sent requests sorted from newer to older.");
        return reqRepository.findAll(pageable).stream()
                .map(reqMapper::toReqDto)
                .filter(reqDto -> reqDto.getState().equals(ReqState.SENT))
                .sorted(Comparator.comparing(ReqDto::getCreatedOn))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReqDto> getAllSentRequestsWithSortFromOldToNew(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from, size);

        log.info("Received a list of all sent requests sorted from older to newer.");
        return reqRepository.findAll(pageable).stream()
                .map(reqMapper::toReqDto)
                .filter(reqDto -> reqDto.getState().equals(ReqState.SENT))
                .sorted(Comparator.comparing(ReqDto::getCreatedOn).reversed())
                .collect(Collectors.toList());
    }

    private Request getRequest(Long reqId) {
        return reqRepository.findById(reqId).orElseThrow(() ->
                new NotFoundException(String.format("Request with id=%d not found", reqId)));
    }
}
