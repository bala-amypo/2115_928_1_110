package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ResourceAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Resource resource;

    @OneToOne
    private ResourceRequest request;

    private LocalDateTime allocatedAt;
    private Boolean conflictFlag;
    private String notes;

    @PrePersist
    void onCreate() {
        this.allocatedAt = LocalDateTime.now();
    }

    // getters & setters
}
