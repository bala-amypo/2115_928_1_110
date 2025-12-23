package com.example.demo.service;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ResourceRequestService {

    private final ResourceRequestRepository requestRepository;
    private final UserRepository userRepository;

    // REQUIRED constructor order
    public ResourceRequestService(ResourceRequestRepository requestRepository,
                                  UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    public ResourceRequest createRequest(Long userId, ResourceRequest request) {

        if (request.getPurpose() == null || request.getPurpose().isEmpty()) {
            throw new ValidationException("Purpose is required");
        }

        if (request.getStartTime().isAfter(request.getEndTime())
                || request.getStartTime().isEqual(request.getEndTime())) {
            throw new ValidationException("Start time must be before end time");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        request.setRequestedBy(user);
        return requestRepository.save(request);
    }

    public List<ResourceRequest> getRequestsByUser(Long userId) {
        return requestRepository.findByRequestedBy_Id(userId);
    }

    public ResourceRequest getRequest(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found"));
    }

    public ResourceRequest updateRequestStatus(Long requestId, String status) {
        ResourceRequest request = getRequest(requestId);
        request.setStatus(status);
        return requestRepository.save(request);
    }
}
