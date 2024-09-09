package com.example.spring_webflux.service;

import com.example.spring_webflux.model.Employee;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Query;
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
        return repository.saveEmployee(employee);
    }

    public Flux<Employee> findAll() {
        return repository.findAll();
    }

    public Mono<Employee> findById(Integer id) {
        return repository.findById(id);
    }


    public Mono<Employee> updateEmployee(Integer id, Employee e) {
        return repository.updateEmployee(id, e);
    }
}



