package com.example.demo.service;

import com.example.demo.entity.Resource;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.ResourceRepository;

public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public Resource createResource(Resource resource) {
        if (resourceRepository.existsByResourceName(resource.getResourceName())) {
            throw new ValidationException("Resource already exists");
        }
        if (resource.getCapacity() < 1) {
            throw new ValidationException("Capacity invalid");
        }
        return resourceRepository.save(resource);
    }
}
