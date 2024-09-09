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

    private final R2dbcEntityTemplate template;

    public EmployeeService(R2dbcEntityTemplate template,EmployeeRepository repository) {
        this.template = template;
        this.repository = repository;
    }



    public Mono<Employee> saveEmployee(Employee employee) {
       return template.insert(employee);
    }

    public Flux<Employee> findAll() {
        return template.select(Employee.class).all();
    }
}



