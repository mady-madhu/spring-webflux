package com.example.spring_webflux.repository;

import com.example.spring_webflux.model.Employee;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<Employee> findById(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Employee.class);
    }

    @Override
    public Mono<Employee> updateEmployee(Integer id, Employee newEmployee) {
        return findById(id)
                .flatMap(existingEmployee -> {
                    existingEmployee.setName(newEmployee.getName());
                    existingEmployee.setRole(newEmployee.getRole());
                    return mongoTemplate.save(existingEmployee);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Employee not found")));
    }

    @Override
    public Flux<Employee> findAll() {
        return mongoTemplate.findAll(Employee.class);
    }
}
