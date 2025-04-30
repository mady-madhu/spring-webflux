package com.example.spring_webflux.service;

import com.example.spring_webflux.model.Employee;
import com.example.spring_webflux.model.EmployeePageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.example.spring_webflux.repository.EmployeeRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Mono<Employee> saveEmployee(Employee employee) {
        return repository.saveEmployee(employee);
    }

    public Flux<Employee> findAll() {
        return repository.findAll();
    }

    public Mono<List<Employee>> findAllAsMono() {
        return repository.findAllAsMono();
    }


    public Mono<Employee> findById(String id) {
        return repository.findById(id);
    }


    public Mono<Employee> updateEmployee(String id, Employee e) {
        return repository.updateEmployee(id, e);
    }

    public Mono<Page<Employee>> getPaginatedEmployees(EmployeePageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }
}




