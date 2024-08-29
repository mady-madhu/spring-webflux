package com.example.spring_webflux.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("employees")
@Data
public class Employee {
    @Id
    private Long id;
    private String name;
    private String role;

    // Getters and setters
}
