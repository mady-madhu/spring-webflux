package com.example.spring_webflux.controller;

import com.example.spring_webflux.model.Employee;
import com.example.spring_webflux.model.EmployeePageRequest;
import com.example.spring_webflux.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/flux")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping("/createEmployee")
    public Mono<Employee> createEmployee(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }


    @GetMapping(value = "/getEmployee", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Employee> getEmployee(@RequestParam String id) {
        return service.findById(id);
    }


    @GetMapping(value = "/getEmployees",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> getAllEmployees() {
        //service.findAll().toStream().collect(C)
        return service.findAll().delayElements(Duration.ofSeconds(3));
    }

    @GetMapping(value = "/getEmployeesAsMono")
    public Mono<List<Employee>> getAllEmployeesAsMono() {
        //service.findAll().toStream().collect(C)
        return service.findAllAsMono();
    }

    @PutMapping(value = "/updateEmployee",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Employee> updateEmployee(@RequestParam String id,@RequestBody Employee e) {
        return service.updateEmployee(id, e);
    }


    @PostMapping("/paginated")
    public Mono<Page<Employee>> paginated(@RequestBody EmployeePageRequest employee) {
        return service.getPaginatedEmployees(employee);
    }



}
