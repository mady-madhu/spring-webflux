package com.example.spring_webflux.repository;

import com.example.spring_webflux.model.Employee;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class EmployeeRepoImpl implements EmployeeRepository {

    private final R2dbcEntityTemplate template;

    public EmployeeRepoImpl(R2dbcEntityTemplate template) {
        this.template = template;
    }

    @Override
    public Mono<Employee> saveEmployee(Employee e) {
        return template.insert(e);
    }

    @Override
    public Mono<Employee> findById(Integer id) {
        return template.
                select(Query.query(Criteria.where("id").is(id))
                        , Employee.class).next();
    }

    public Mono<Employee> updateEmployee(Integer id, Employee newEmployee) {
        // Find the existing employee
        return template.select(Employee.class)
                .matching(Query.query(Criteria.where("id").is(id)))
                .one()
                .flatMap(existingEmployee -> {
                    if (existingEmployee == null) {
                        return Mono.error(new RuntimeException("Employee not found"));
                    }
                    // Update the existing employee with new values
                    existingEmployee.setName(newEmployee.getName());
                    existingEmployee.setRole(newEmployee.getRole());
                    // Save the updated employee
                    return template.update(existingEmployee);
                });
    }
    @Override
    public Flux<Employee> findAll() {
        return template.select(Employee.class).all();
    }
}
