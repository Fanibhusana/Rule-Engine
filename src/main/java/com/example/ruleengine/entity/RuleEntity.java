package com.example.ruleengine.entity;

import java.time.LocalDateTime;

import com.example.ruleengine.model.Node;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rules")
public class RuleEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String ruleString;  // Raw rule string

    @Lob
    private String astJson;  // Serialized AST (JSON format)

    private LocalDateTime createdAt;
}
