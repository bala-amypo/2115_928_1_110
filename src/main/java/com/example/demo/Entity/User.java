package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String role;
    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        if (role == null) role = "USER";
        this.createdAt = LocalDateTime.now();
    }

    // getters & setters
}
