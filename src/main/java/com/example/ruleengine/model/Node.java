package com.example.ruleengine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Node {

	private String type; // "operator" or "operand"
    private Node left;   // Left child (for operators like AND/OR)
    private Node right;  // Right child (for operators like AND/OR)
    private String value; // Operand value or operator (e.g., AND, age > 30)
}
