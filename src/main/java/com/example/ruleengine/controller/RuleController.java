package com.example.ruleengine.controller;


import com.example.ruleengine.model.Node;
import com.example.ruleengine.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public class RuleController {

	@Autowired
    private RuleService ruleService;

    // Endpoint to create a rule
    @PostMapping("/create")
    public ResponseEntity<Node> createRule(@RequestBody String ruleString) {
        Node rule = ruleService.createRule(ruleString);
        return ResponseEntity.ok(rule);
    }

    // Endpoint to evaluate a rule against user data
    @PostMapping("/evaluate")
    public ResponseEntity<Boolean> evaluateRule(@RequestBody Map<String, Object> userData) {
        // Assume you pass AST and userData for simplicity
        Node rule = ruleService.createRule("age > 30 AND department = 'Sales'");  // Demo rule
        boolean result = ruleService.evaluateRule(rule, userData);
        return ResponseEntity.ok(result);
    }
}
