package com.dressbookbackend.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tailor")
public class Tailor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;

    private String lastName;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "sewing_station_id")
    private SewingStation sewingStation;

    @ManyToMany
    @JoinTable(
            name = "tailor_garment_type",
            joinColumns = @JoinColumn(name = "tailor_id"),
            inverseJoinColumns = @JoinColumn(name = "garment_type_id")
    )
    private List<GarmentType> garmentTypes = new ArrayList<>();

    public Tailor() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public SewingStation getSewingStation() {
        return sewingStation;
    }

    public void setSewingStation(SewingStation sewingStation) {
        this.sewingStation = sewingStation;
    }

    public List<GarmentType> getGarmentTypes() {
        return garmentTypes;
    }

    public void setGarmentTypes(List<GarmentType> garmentTypes) {
        this.garmentTypes = garmentTypes;
    }
}