package com.example.ruleengine.service;
import com.example.ruleengine.model.Node;

import java.util.Map;

import org.springframework.stereotype.Service;
public class RuleService {

	  // Method to parse rule string and generate AST
    public Node createRule(String ruleString) {
        // Simplified parsing logic: Use a tokenizer or regex to split the string
        // (age > 30 AND department = 'Sales')
        // Convert this into an AST
        // For now, we're assuming simple parsing for demo purposes.
        Node rootNode = parseRule(ruleString);
        return rootNode;
    }

    
    private Node parseRule(String ruleString) {
        // Implement a basic parsing logic to construct AST here
        // For now, assume fixed structure
        return new Node("operator", new Node("operand", null, null, "age > 30"), 
                        new Node("operand", null, null, "department = 'Sales'"), "AND");
    }
    
    public boolean evaluateRule(Node node, Map<String, Object> userData) {
        if ("operand".equals(node.getType())) {
            return evaluateCondition(node.getValue(), userData);
        } else if ("operator".equals(node.getType())) {
            boolean leftEval = evaluateRule(node.getLeft(), userData);
            boolean rightEval = evaluateRule(node.getRight(), userData);
            if ("AND".equals(node.getValue())) {
                return leftEval && rightEval;
            } else if ("OR".equals(node.getValue())) {
                return leftEval || rightEval;
            }
        }
        return false;
    }

    private boolean evaluateCondition(String condition, Map<String, Object> userData) {
        // Step 1: Split the condition into attribute, operator, and value.
        condition = condition.trim();

        // Handle basic conditions like "age > 30", "department = 'Sales'"
        // Regex to match conditions (e.g. "age > 30" or "department = 'Sales'")
        String[] parts = condition.split(" ");

        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid condition format: " + condition);
        }

        String attribute = parts[0];  // e.g. "age"
        String operator = parts[1];   // e.g. ">", "=", "<", etc.
        String value = condition.substring(condition.indexOf(parts[2])); // Everything after operator (may be a number or string)

        // Step 2: Retrieve the value from userData for the given attribute
        Object userValue = userData.get(attribute);
        if (userValue == null) {
            throw new IllegalArgumentException("Attribute not found in user data: " + attribute);
        }

        // Step 3: Evaluate based on the operator
        switch (operator) {
            case ">":
                return compareGreater(userValue, value);
            case "<":
                return compareLesser(userValue, value);
            case ">=":
                return compareGreaterOrEqual(userValue, value);
            case "<=":
                return compareLesserOrEqual(userValue, value);
            case "=":
            case "==":
                return compareEquals(userValue, value);
            case "!=":
                return !compareEquals(userValue, value);
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }

    // Helper methods for comparison logic

    private boolean compareGreater(Object userValue, String value) {
        if (userValue instanceof Number) {
            return ((Number) userValue).doubleValue() > Double.parseDouble(value);
        }
        throw new IllegalArgumentException("Invalid comparison for non-numeric attribute");
    }

    private boolean compareLesser(Object userValue, String value) {
        if (userValue instanceof Number) {
            return ((Number) userValue).doubleValue() < Double.parseDouble(value);
        }
        throw new IllegalArgumentException("Invalid comparison for non-numeric attribute");
    }

    private boolean compareGreaterOrEqual(Object userValue, String value) {
        if (userValue instanceof Number) {
            return ((Number) userValue).doubleValue() >= Double.parseDouble(value);
        }
        throw new IllegalArgumentException("Invalid comparison for non-numeric attribute");
    }

    private boolean compareLesserOrEqual(Object userValue, String value) {
        if (userValue instanceof Number) {
            return ((Number) userValue).doubleValue() <= Double.parseDouble(value);
        }
        throw new IllegalArgumentException("Invalid comparison for non-numeric attribute");
    }

    private boolean compareEquals(Object userValue, String value) {
        if (userValue instanceof Number) {
            return userValue.toString().equals(value);
        } else if (userValue instanceof String) {
            // Handle string equality with or without quotes
            String cleanValue = value.replaceAll("^['\"]|['\"]$", "");  // Remove quotes if present
            return userValue.equals(cleanValue);
        }
        throw new IllegalArgumentException("Unsupported type for equality comparison");
    }


}
