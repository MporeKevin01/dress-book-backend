package com.dressbookbackend.controller;

import com.dressbookbackend.entity.Customer;
import com.dressbookbackend.entity.TailoringOrder;
import com.dressbookbackend.enums.TailoringStatus;
import com.dressbookbackend.service.TailoringOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tailoring-orders")
public class TailoringOrderController {

    private final TailoringOrderService service;

    public TailoringOrderController(
            TailoringOrderService service) {

        this.service = service;
    }

    // A - CREATE

    @PostMapping("/save")
    public String save(
            @RequestBody TailoringOrder order) {

        return service.save(order);
    }

    // A - READ ALL

    @GetMapping("/all")
    public List<TailoringOrder> getAll() {

        return service.getAll();
    }

    // A - READ BY ID

    @GetMapping("/{id}")
    public TailoringOrder getById(
            @PathVariable UUID id) {

        return service.getById(id).orElse(null);
    }

    // A - UPDATE

    @PutMapping("/update/{id}")
    public String update(
            @PathVariable UUID id,
            @RequestBody TailoringOrder order) {

        return service.update(id, order);
    }

    // E3 - DELETE

    @DeleteMapping("/delete/{id}")
    public String delete(
            @PathVariable UUID id) {

        return service.delete(id);
    }

    // B2 - ORDERS BY STATUS

    @GetMapping("/status/{status}")
    public List<TailoringOrder> getOrdersByStatus(
            @PathVariable TailoringStatus status) {

        return service.getOrdersByStatus(status);
    }

    // B3 - ORDERS BETWEEN DATES

    @GetMapping("/between")
    public List<TailoringOrder> between(
            @RequestParam String start,
            @RequestParam String end) {

        return service.between(start, end);
    }

    // C4 - CUSTOMERS OF TAILOR

    @GetMapping("/tailor-customers/{tailorId}")
    public List<Customer> customersOfTailor(
            @PathVariable UUID tailorId) {

        return service.customersOfTailor(tailorId);
    }

    // D2 - STATUS FLOW

    @PatchMapping("/{id}/status")
    public String updateStatus(
            @PathVariable UUID id,
            @RequestParam TailoringStatus status) {

        return service.updateStatus(id, status);
    }

    // F1 - TAILORING ORDERS PER STATUS

    @GetMapping("/stats/by-status")
    public List<Object[]> countOrdersByStatus() {

        return service.countOrdersByStatus();
    }

    // F2 - TAILOR IS ABSENT

    @PatchMapping("/cancel-day")
    public String cancelDay(
            @RequestParam UUID tailorId,
            @RequestParam String date) {

        return service.cancelDay(
                tailorId,
                date);
    }
}