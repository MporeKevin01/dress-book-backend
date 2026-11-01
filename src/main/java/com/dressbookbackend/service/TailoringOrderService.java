package com.dressbookbackend.service;

import com.dressbookbackend.entity.Customer;
import com.dressbookbackend.entity.TailoringOrder;
import com.dressbookbackend.enums.TailoringStatus;
import com.dressbookbackend.repository.AlterationRepository;
import com.dressbookbackend.repository.TailoringOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TailoringOrderService {

    private final TailoringOrderRepository repository;
    private final AlterationRepository alterationRepository;

    public TailoringOrderService(
            TailoringOrderRepository repository,
            AlterationRepository alterationRepository) {

        this.repository = repository;
        this.alterationRepository = alterationRepository;
    }

    // D1 - SAVE ORDER

    public String save(TailoringOrder order) {

        if (order.getDueDate().isBefore(LocalDate.now())) {
            return "Tailoring order date cannot be in the past";
        }

        if (repository.existsByTailorAndDueDateAndStatusNot(
                order.getTailor(),
                order.getDueDate(),
                TailoringStatus.CANCELLED)) {

            return "Tailor already has an order due on that date";
        }

        order.setStatus(TailoringStatus.ORDERED);

        repository.save(order);

        return "Order saved successfully";
    }

    // A - READ ALL

    public List<TailoringOrder> getAll() {

        return repository.findAll();
    }

    // A - READ BY ID

    public Optional<TailoringOrder> getById(UUID id) {

        return repository.findById(id);
    }

    // A - UPDATE

    public String update(
            UUID id,
            TailoringOrder order) {

        TailoringOrder existing =
                repository.findById(id)
                        .orElse(null);

        if (existing == null) {
            return "Order not found";
        }

        existing.setDueDate(order.getDueDate());
        existing.setGarmentDescription(
                order.getGarmentDescription());
        existing.setPrice(order.getPrice());
        existing.setCustomer(order.getCustomer());
        existing.setTailor(order.getTailor());

        repository.save(existing);

        return "Order updated successfully";
    }

    // E3 - DELETE

    public String delete(UUID id) {

        TailoringOrder order =
                repository.findById(id)
                        .orElse(null);

        if (order == null) {
            return "Order not found";
        }

        if (alterationRepository
                .existsByTailoringOrder(order)) {

            return "That tailoring order has alterations, delete them first";
        }

        repository.delete(order);

        return "Order deleted successfully";
    }

    // B2 - ORDERS BY STATUS

    public List<TailoringOrder> getOrdersByStatus(
            TailoringStatus status) {

        return repository
                .findByStatusOrderByDueDateAsc(status);
    }

    // B3 - ORDERS BETWEEN DATES

    public List<TailoringOrder> between(
            String start,
            String end) {

        return repository
                .findByDueDateBetweenOrderByDueDate(
                        LocalDate.parse(start),
                        LocalDate.parse(end));
    }

    // C4 - CUSTOMERS OF TAILOR

    public List<Customer> customersOfTailor(
            UUID tailorId) {

        return repository.customersOfTailor(tailorId);
    }

    // D2 - STATUS FLOW

    public String updateStatus(
            UUID id,
            TailoringStatus status) {

        TailoringOrder order =
                repository.findById(id)
                        .orElse(null);

        if (order == null) {
            return "Order not found";
        }

        if (!allowed(order.getStatus(), status)) {
            return "Invalid status transition";
        }

        order.setStatus(status);

        repository.save(order);

        return "Status updated successfully";
    }

    private boolean allowed(
            TailoringStatus current,
            TailoringStatus next) {

        if (current == TailoringStatus.ORDERED) {

            return next == TailoringStatus.STITCHING
                    || next == TailoringStatus.CANCELLED;
        }

        if (current == TailoringStatus.STITCHING) {

            return next == TailoringStatus.READY
                    || next == TailoringStatus.CANCELLED;
        }

        return false;
    }

    // F1 - ORDERS PER STATUS

    public List<Object[]> countOrdersByStatus() {

        return repository.countOrdersByStatus();
    }

    // F2 - TAILOR ABSENT

    public String cancelDay(
            UUID tailorId,
            String date) {

        int count =
                repository.cancelOrdersForTailorOnDate(
                        tailorId,
                        LocalDate.parse(date));

        return count + " tailoring orders cancelled";
    }
}