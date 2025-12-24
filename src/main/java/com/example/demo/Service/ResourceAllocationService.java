package com.example.demo.service;

import com.example.demo.entity.Resource;
import com.example.demo.entity.ResourceAllocation;
import com.example.demo.entity.ResourceRequest;
import com.example.demo.repository.ResourceAllocationRepository;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.repository.ResourceRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResourceAllocationService {

    private final ResourceRequestRepository resourceRequestRepository;
    private final ResourceRepository resourceRepository;
    private final ResourceAllocationRepository resourceAllocationRepository;

    public ResourceAllocationService(
            ResourceRequestRepository resourceRequestRepository,
            ResourceRepository resourceRepository,
            ResourceAllocationRepository resourceAllocationRepository) {

        this.resourceRequestRepository = resourceRequestRepository;
        this.resourceRepository = resourceRepository;
        this.resourceAllocationRepository = resourceAllocationRepository;
    }

    // Auto allocate resource based on resource type
    public ResourceAllocation autoAllocate(Long requestId) {

        ResourceRequest request =
                resourceRequestRepository.findById(requestId)
                        .orElseThrow(() -> new RuntimeException("Request not found"));

        Resource resource =
                resourceRepository.findByResourceType(request.getResourceType())
                        .stream()
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Resource not available"));

        ResourceAllocation allocation = new ResourceAllocation();
        allocation.setRequest(request);
        allocation.setResource(resource);
        allocation.setConflictFlag(false);
        allocation.setNotes("Auto allocated");
        allocation.setAllocatedAt(LocalDateTime.now());

        return resourceAllocationRepository.save(allocation);
    }

    // Get allocation by ID
    public ResourceAllocation getAllocation(Long id) {
        return resourceAllocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Allocation not found"));
    }

    // Get all allocations
    public List<ResourceAllocation> getAllAllocations() {
        return resourceAllocationRepository.findAll();
    }
}
