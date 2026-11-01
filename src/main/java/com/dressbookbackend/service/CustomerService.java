package com.dressbookbackend.service;

import com.dressbookbackend.entity.Customer;
import com.dressbookbackend.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    // A - CREATE

    public String save(Customer customer) {

        repository.save(customer);

        return "Customer saved successfully";
    }

    // A - READ ALL

    public List<Customer> getAll() {

        return repository.findAll();
    }

    // A - READ BY ID

    public Optional<Customer> getById(UUID id) {

        return repository.findById(id);
    }

    // A - UPDATE

    public String update(UUID id, Customer customer) {

        Customer existing =
                repository.findById(id)
                        .orElse(null);

        if (existing == null) {
            return "Customer not found";
        }

        existing.setFirstName(customer.getFirstName());
        existing.setLastName(customer.getLastName());
        existing.setPhone(customer.getPhone());
        existing.setEmail(customer.getEmail());

        repository.save(existing);

        return "Customer updated successfully";
    }

    // A - DELETE

    public String delete(UUID id) {

        if (!repository.existsById(id)) {
            return "Customer not found";
        }

        repository.deleteById(id);

        return "Customer deleted successfully";
    }

    // B1

    public List<Customer> byLastName(String lastName) {

        return repository
                .findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }
}