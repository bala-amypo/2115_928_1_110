package com.example.demo.service;

import com.example.demo.entity.AllocationRule;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AllocationRuleRepository;
import org.springframework.stereotype.Service;

@Service   // ⭐ VERY IMPORTANT
public class AllocationRuleService {

    private final AllocationRuleRepository repository;

    // Constructor Injection (BEST PRACTICE)
    public AllocationRuleService(AllocationRuleRepository repository) {
        this.repository = repository;
    }

    // Create a new allocation rule
    public AllocationRule createRule(AllocationRule rule) {

        if (rule == null) {
            throw new ValidationException("Rule cannot be null");
        }

        if (rule.getRuleName() == null || rule.getRuleName().isEmpty()) {
            throw new ValidationException("Rule name is required");
        }

        if (repository.existsByRuleName(rule.getRuleName())) {
            throw new ValidationException("Rule already exists");
        }

        return repository.save(rule);
    }
}
