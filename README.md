# Rule Engine with AST (Abstract Syntax Tree)

This project implements a rule engine that allows users to define, combine, and evaluate rules based on attributes like age, department, and salary. The rules are parsed into an Abstract Syntax Tree (AST), allowing for flexible rule creation and dynamic evaluation against user data.

## Features

- **AST-based Rule Parsing**: The rules are parsed into a tree structure for flexible evaluation.
- **Rule Evaluation**: Evaluate complex rules with logical operators (AND, OR) and conditions.
- **Combine Multiple Rules**: Build and combine multiple conditions dynamically.
- **Extensible**: Easy to extend the rules and operators supported by the engine.

## Project Structure

```plaintext
rule-engine-ast/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── ruleengine/
│   │   │           ├── model/
│   │   │           │   └── Node.java           # AST Node class
│   │   │           ├── parser/
│   │   │           │   └── RuleParser.java     # Rule parsing logic
│   │   │           ├── evaluator/
│   │   │           │   └── RuleEvaluator.java  # Rule evaluation logic
│   │   │           ├── Main.java               # Main class to run the app
│   ├── test/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── ruleengine/
│   │   │           └── RuleEngineTest.java     # Unit tests for parser and evaluator
├── pom.xml                                      # Maven dependencies and build config
└── README.md                                    # This file
````
## Getting Started

### Prerequisites

- **Java 11** or higher
- **Maven 3.6+**

