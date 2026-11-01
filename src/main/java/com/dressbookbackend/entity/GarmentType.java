package com.dressbookbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "garment_type")
public class GarmentType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "garmentTypes")
    private List<Tailor> tailors = new ArrayList<>();

    public GarmentType() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tailor> getTailors() {
        return tailors;
    }

    public void setTailors(List<Tailor> tailors) {
        this.tailors = tailors;
    }
}