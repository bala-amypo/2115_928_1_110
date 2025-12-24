package com.example.demo.controller;

import com.example.demo.entity.ResourceRequest;
import com.example.demo.repository.ResourceRequestRepository;
import com.example.demo.service.ResourceRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class ResourceRequestController {

    private final ResourceRequestService service;
    private final ResourceRequestRepository repository;

    public ResourceRequestController(ResourceRequestService service,
                                     ResourceRequestRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    // Create a request for a specific user
    @PostMapping("/{userId}")
    public ResourceRequest createRequest(@PathVariable Long userId,
                                         @RequestBody ResourceRequest request) {
        return service.createRequest(userId, request);
    }

    // Get all requests by a user
    @GetMapping("/user/{userId}")
    public List<ResourceRequest> getRequestsByUser(@PathVariable Long userId) {
        return repository.findByRequestedBy_Id(userId);
    }

    // Get a request by ID
    @GetMapping("/{id}")
    public ResourceRequest getRequest(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> 
                new RuntimeException("Request not found"));
    }

    // Update status of a request
    @PutMapping("/status/{requestId}")
    public ResourceRequest updateStatus(@PathVariable Long requestId,
                                        @RequestParam String status) {
        ResourceRequest req = repository.findById(requestId).orElseThrow(() -> 
                new RuntimeException("Request not found"));
        req.setStatus(status);   // ✅ This now works with entity setter
        return repository.save(req);
    }
}
