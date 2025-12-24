package com.example.demo.service;

import com.example.demo.entity.AllocationRule;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AllocationRuleRepository;

public class AllocationRuleService {

    private final AllocationRuleRepository repository;

    public AllocationRuleService(AllocationRuleRepository repository) {
        this.repository = repository;
    }

    public AllocationRule createRule(AllocationRule rule) {
        if (repository.existsByRuleName(rule.getRuleName())) {
            throw new ValidationException("Rule already exists");
        }
        return repository.save(rule);
    }
}
