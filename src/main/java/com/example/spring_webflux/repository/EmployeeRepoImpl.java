package com.example.spring_webflux.repository;

import com.example.spring_webflux.model.Employee;
import com.example.spring_webflux.model.EmployeePageRequest;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class EmployeeRepoImpl implements EmployeeRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    public EmployeeRepoImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<Employee> saveEmployee(Employee e) {
        return mongoTemplate.save(e);
    }

    @Override
    public Mono<Employee> findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Employee.class);
    }

    @Override
    public Mono<Employee> updateEmployee(String id, Employee newEmployee) {
        return findById(id)
                .flatMap(existingEmployee -> {
                    existingEmployee.setName(newEmployee.getName());
                    existingEmployee.setEmail(newEmployee.getEmail());
                    existingEmployee.setRole(newEmployee.getRole());
                    return mongoTemplate.save(existingEmployee);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Employee not found")));
    }

    @Override
    public Flux<Employee> findAll() {
        return mongoTemplate.findAll(Employee.class);
    }

    @Override
    public Mono<List<Employee>> findAllAsMono() {
        return mongoTemplate.findAll(Employee.class).collectList();
    }


    @Override
    public Mono<Page<Employee>> findAll(EmployeePageRequest pageRequest) {
        Query query = new Query()
                .with(Sort.by(Sort.Order.by(pageRequest.getSortField()).with(Sort.Direction.fromString(pageRequest.getSortDirection()))))
                .skip(pageRequest.getPage() * pageRequest.getSize())  // Pagination offset
                .limit(pageRequest.getSize());  // Pagination limit

        // Execute the query to fetch the employees in the given page and sorted order
        Flux<Employee> employeesFlux = mongoTemplate.find(query, Employee.class);

        // Count the total number of employees for pagination metadata
        Mono<Long> countMono = mongoTemplate.count(new Query(), Employee.class);

        // Combine the results into a Page
        return countMono.zipWith(employeesFlux.collectList())
                .map(tuple -> {
                    long totalElements = tuple.getT1();
                    return new PageImpl<>(tuple.getT2(), PageRequest.of(pageRequest.getPage(), pageRequest.getSize()), totalElements);
                });
    }
}
