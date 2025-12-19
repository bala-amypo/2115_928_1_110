package com.example.demo.controller;

import com.example.demo.entity.ResourceAllocation;
import com.example.demo.service.ResourceAllocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocations")
public class ResourceAllocationController {

    private final ResourceAllocationService allocationService;

    public ResourceAllocationController(ResourceAllocationService allocationService) {
        this.allocationService = allocationService;
    }

    @PostMapping("/auto/{requestId}")
    public ResourceAllocation autoAllocate(@PathVariable Long requestId) {
        return allocationService.autoAllocate(requestId);
    }

    @GetMapping
    public List<ResourceAllocation> getAll() {
        return allocationService.getAllAllocations();
    }

    @GetMapping("/{id}")
    public ResourceAllocation getById(@PathVariable Long id) {
        return allocationService.getAllocation(id);
    }
}
