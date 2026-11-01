package com.dressbookbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "alteration")
public class Alteration {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String part;

    private String instruction;

    private int centimetres;

    private LocalDate requestedDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tailoring_order_id")
    private TailoringOrder tailoringOrder;

    public Alteration() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getCentimetres() {
        return centimetres;
    }

    public void setCentimetres(int centimetres) {
        this.centimetres = centimetres;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public TailoringOrder getTailoringOrder() {
        return tailoringOrder;
    }

    public void setTailoringOrder(TailoringOrder tailoringOrder) {
        this.tailoringOrder = tailoringOrder;
    }
}