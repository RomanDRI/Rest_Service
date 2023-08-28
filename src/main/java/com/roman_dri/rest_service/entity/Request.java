package com.roman_dri.rest_service.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_message")
    private String userMessage;

    @Enumerated(EnumType.STRING)
    private ReqState state;

    @ManyToOne
    private User user;

    @Column(name = "created_on")
    private LocalDateTime createdOn;
}
