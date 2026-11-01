package com.dressbookbackend.controller;

import com.dressbookbackend.entity.GarmentType;
import com.dressbookbackend.service.GarmentTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/garment-types")
public class GarmentTypeController {

    private final GarmentTypeService service;

    public GarmentTypeController(
            GarmentTypeService service) {

        this.service = service;
    }

    // A - CREATE

    @PostMapping("/save")
    public String save(
            @RequestBody GarmentType garmentType) {

        return service.save(garmentType);
    }

    // A - READ ALL

    @GetMapping("/all")
    public List<GarmentType> getAll() {

        return service.getAll();
    }

    // A - READ BY ID

    @GetMapping("/{id}")
    public GarmentType getById(
            @PathVariable UUID id) {

        return service.getById(id).orElse(null);
    }

    // A - UPDATE

    @PutMapping("/update/{id}")
    public String update(
            @PathVariable UUID id,
            @RequestBody GarmentType garmentType) {

        return service.update(id, garmentType);
    }

    // A - DELETE

    @DeleteMapping("/delete/{id}")
    public String delete(
            @PathVariable UUID id) {

        return service.delete(id);
    }

    // C3 - UNUSED GARMENT TYPES

    @GetMapping("/unused")
    public List<GarmentType> unused() {

        return service.unused();
    }
}