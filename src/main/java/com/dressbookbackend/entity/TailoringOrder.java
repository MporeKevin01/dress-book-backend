package com.dressbookbackend.entity;

import com.dressbookbackend.enums.TailoringStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tailoring_order")
public class TailoringOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private TailoringStatus status;

    private String garmentDescription;

    private double price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "tailor_id")
    private Tailor tailor;

    public TailoringOrder() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TailoringStatus getStatus() {
        return status;
    }

    public void setStatus(TailoringStatus status) {
        this.status = status;
    }

    public String getGarmentDescription() {
        return garmentDescription;
    }

    public void setGarmentDescription(String garmentDescription) {
        this.garmentDescription = garmentDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Tailor getTailor() {
        return tailor;
    }

    public void setTailor(Tailor tailor) {
        this.tailor = tailor;
    }
}