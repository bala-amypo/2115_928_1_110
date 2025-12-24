package com.example.demo.controller;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ResourceRequestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requests")
public class ResourceRequestController {

    private final ResourceRequestService resourceRequestService;
    private final UserRepository userRepository;

    public ResourceRequestController(ResourceRequestService resourceRequestService,
                                     UserRepository userRepository) {
        this.resourceRequestService = resourceRequestService;
        this.userRepository = userRepository;
    }

    @PostMapping("/{userId}")
    public ResourceRequest createRequest(
            @PathVariable Long userId,
            @RequestBody ResourceRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return resourceRequestService.createRequest(request, user);
    }
}
