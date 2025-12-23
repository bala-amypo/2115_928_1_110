package com.example.demo.controller;

import com.example.demo.entity.ResourceAllocation;
import com.example.demo.service.ResourceAllocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocations")
public class ResourceAllocationController {

    private final ResourceAllocationService service;

    public ResourceAllocationController(ResourceAllocationService service) {
        this.service = service;
    }

    @PostMapping("/{requestId}")
    public ResourceAllocation autoAllocate(@PathVariable Long requestId) {
        return service.autoAllocate(requestId);
    }

    @GetMapping("/{id}")
    public ResourceAllocation getAllocation(@PathVariable Long id) {
        return service.getAllocation(id);
    }

    @GetMapping
    public List<ResourceAllocation> getAllAllocations() {
        return service.getAllAllocations();
    }
}
