package com.example.spring_webflux.service;

import com.example.spring_webflux.model.Employee;
import org.springframework.stereotype.Service;
import com.example.spring_webflux.repository.EmployeeRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Mono<Employee> saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Flux<Employee> getEmployeesByRole(String role) {
        return repository.findByRole(role);
    }
}
