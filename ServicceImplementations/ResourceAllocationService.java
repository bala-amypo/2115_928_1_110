package com.example.demo.service;

import com.example.demo.entity.Resource;
import com.example.demo.entity.ResourceAllocation;
import com.example.demo.entity.ResourceRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ResourceAllocationRepository;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.repository.ResourceRequestRepository;

import java.util.List;

public class ResourceAllocationService {

    private final ResourceRequestRepository requestRepository;
    private final ResourceRepository resourceRepository;
    private final ResourceAllocationRepository allocationRepository;

    // REQUIRED constructor order
    public ResourceAllocationService(ResourceRequestRepository requestRepository,
                                     ResourceRepository resourceRepository,
                                     ResourceAllocationRepository allocationRepository) {
        this.requestRepository = requestRepository;
        this.resourceRepository = resourceRepository;
        this.allocationRepository = allocationRepository;
    }

    public ResourceAllocation autoAllocate(Long requestId) {

        ResourceRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found"));

        List<Resource> resources =
                resourceRepository.findByResourceType(request.getResourceType());

        if (resources.isEmpty()) {
            throw new ResourceNotFoundException("No resource available");
        }

        // FIRST_AVAILABLE logic
        Resource selected = resources.get(0);

        ResourceAllocation allocation = new ResourceAllocation();
        allocation.setRequest(request);
        allocation.setResource(selected);
        allocation.setConflictFlag(false);
        allocation.setNotes("Auto allocated");

        request.setStatus("APPROVED");

        return allocationRepository.save(allocation);
    }

    public ResourceAllocation getAllocation(Long id) {
        return allocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Allocation not found"));
    }

    public List<ResourceAllocation> getAllAllocations() {
        return allocationRepository.findAll();
    }
}
