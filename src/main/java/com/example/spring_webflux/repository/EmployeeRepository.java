package com.example.spring_webflux.repository;

import com.example.spring_webflux.model.Employee;
import com.example.spring_webflux.model.EmployeePageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//in my case this extends ReactiveCrudRepository<Employee, Integer> is not working i need to investigate

@Repository
public interface EmployeeRepository {

    Mono<Employee> saveEmployee(Employee e);

    Mono<Employee> findById(String id);


    Mono<Employee> updateEmployee(String id, Employee e);

    Flux<Employee> findAll();

    Mono<Page<Employee>> findAll(EmployeePageRequest pageRequest);
}
