package com.example.spring_webflux.controller;

import com.example.spring_webflux.model.Employee;
import com.example.spring_webflux.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/flux/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public Mono<Employee> createEmployee(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    @GetMapping("/role/{role}")
    public Flux<Employee> getEmployeesByRole(@PathVariable String role) {
        return service.getEmployeesByRole(role);
    }
}
