package com.roman_dri.rest_service.repository;

import com.roman_dri.rest_service.entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface ReqRepository extends JpaRepository<Request, Long> {
    Page<Request> findAllByUserIdOrderByCreatedOnAsc(Long userId, Pageable pageable);

    Page<Request> findAllByUserIdOrderByCreatedOnDesc(Long userId, Pageable pageable);

    Optional<Request> findByIdAndUserId(Long reqId, Long userId);
}
