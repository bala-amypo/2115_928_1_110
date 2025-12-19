package com.example.demo.controller;

import com.example.demo.entity.Resource;
import com.example.demo.service.ResourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    public Resource create(@RequestBody Resource resource) {
        return resourceService.createResource(resource);
    }

    @GetMapping
    public List<Resource> getAll() {
        return resourceService.getAllResources();
    }

    @GetMapping("/{id}")
    public Resource getById(@PathVariable Long id) {
        return resourceService.getResource(id);
    }
}
