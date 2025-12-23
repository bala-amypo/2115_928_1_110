package com.example.demo.controller;

import com.example.demo.entity.AllocationRule;
import com.example.demo.repository.AllocationRuleRepository;
import com.example.demo.service.AllocationRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class AllocationRuleController {

    private final AllocationRuleService service;
    private final AllocationRuleRepository repository;

    public AllocationRuleController(AllocationRuleService service,
                                    AllocationRuleRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping
    public AllocationRule createRule(@RequestBody AllocationRule rule) {
        return service.createRule(rule);
    }

    @GetMapping
    public List<AllocationRule> getAllRules() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public AllocationRule getRule(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }
}
