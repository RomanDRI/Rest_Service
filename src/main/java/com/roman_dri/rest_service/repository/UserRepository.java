package com.roman_dri.rest_service.repository;

import com.roman_dri.rest_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users " +
            "WHERE (LOWER(name) LIKE '%' || ?1 || '%')",
            nativeQuery = true)
    User findUserByName(String query);
}
