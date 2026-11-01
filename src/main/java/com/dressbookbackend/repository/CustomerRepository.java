package com.dressbookbackend.repository;

import com.dressbookbackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


public interface CustomerRepository
        extends JpaRepository<Customer, UUID> {

    // B1

    List<Customer>
    findByLastNameIgnoreCaseOrderByFirstNameAsc(
            String lastName);
}
