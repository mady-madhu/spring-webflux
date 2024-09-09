package com.example.spring_webflux.controller;

import com.example.spring_webflux.model.Employee;
import com.example.spring_webflux.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/flux/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public Mono<Employee> createEmployee(@RequestBody Employee employee) {
        return service.saveEmployee( employee);
    }


    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> getAllEmployees() {
        return service.findAll().delayElements(Duration.ofSeconds(3));
    }



}
