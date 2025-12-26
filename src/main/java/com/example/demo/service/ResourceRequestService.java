package com.example.demo.service;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.ResourceRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceRequestService {

    private final ResourceRequestRepository repository;

    public ResourceRequestService(ResourceRequestRepository repository) {
        this.repository = repository;
    }

    // Create request
    public ResourceRequest createRequest(ResourceRequest request, User user) {

        if (request == null) {
            throw new ValidationException("Request cannot be null");
        }

        if (request.getStartTime() == null || request.getEndTime() == null) {
            throw new ValidationException("Start time and End time are required");
        }

        if (request.getEndTime().isBefore(request.getStartTime())) {
            throw new ValidationException("End time must be after start time");
        }

        request.setRequestedBy(user);
        request.setStatus("PENDING");

        return repository.save(request);
    }

    // Get all requests
    public List<ResourceRequest> getAllRequests() {
        return repository.findAll();
    }
}
