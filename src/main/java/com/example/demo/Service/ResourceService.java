package com.example.demo.service;

import com.example.demo.entity.Resource;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ⭐ THIS WAS MISSING
public class ResourceService {

    private final ResourceRepository repository;

    // Constructor Injection
    public ResourceService(ResourceRepository repository) {
        this.repository = repository;
    }

    // Create Resource
    public Resource createResource(Resource resource) {

        if (resource == null) {
            throw new ValidationException("Resource cannot be null");
        }

        if (resource.getResourceName() == null || resource.getResourceName().isEmpty()) {
            throw new ValidationException("Resource name is required");
        }

        if (resource.getCapacity() <= 0) {
            throw new ValidationException("Capacity must be greater than 0");
        }

        return repository.save(resource);
    }

    // Get all resources
    public List<Resource> getAllResources() {
        return repository.findAll();
    }
}
