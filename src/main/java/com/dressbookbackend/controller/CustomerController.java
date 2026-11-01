package com.dressbookbackend.controller;

import com.dressbookbackend.entity.Customer;
import com.dressbookbackend.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    // A - CREATE
    @PostMapping("/save")
    public String save(@RequestBody Customer customer) {
        return service.save(customer);
    }

    // A - READ ALL
    @GetMapping("/all")
    public List<Customer> getAll() {
        return service.getAll();
    }

    // A - READ BY ID
    @GetMapping("/{id}")
    public Customer getById(@PathVariable UUID id) {
        return service.getById(id).orElse(null);
    }

    // A - UPDATE
    @PutMapping("/update/{id}")
    public String update(
            @PathVariable UUID id,
            @RequestBody Customer customer) {

        return service.update(id, customer);
    }

    // A - DELETE
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        return service.delete(id);
    }

    // B1 - FIND BY LAST NAME
    @GetMapping("/by-last-name")
    public List<Customer> findByLastName(
            @RequestParam String lastName) {

        return service.byLastName(lastName);
    }
}