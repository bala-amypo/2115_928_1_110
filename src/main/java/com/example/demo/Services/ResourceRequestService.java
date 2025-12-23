package com.example.demo.service;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.repository.UserRepository;

public class ResourceRequestService {

    private final ResourceRequestRepository requestRepository;
    private final UserRepository userRepository;

    public ResourceRequestService(ResourceRequestRepository r, UserRepository u) {
        this.requestRepository = r;
        this.userRepository = u;
    }

    public ResourceRequest createRequest(Long userId, ResourceRequest request) {
        if (request.getStartTime().isAfter(request.getEndTime())) {
            throw new ValidationException("Time error");
        }
        request.setRequestedBy(userRepository.findById(userId).orElseThrow());
        return requestRepository.save(request);
    }
}
