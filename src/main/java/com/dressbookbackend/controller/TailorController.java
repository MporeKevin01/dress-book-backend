package com.dressbookbackend.controller;

import com.dressbookbackend.entity.Tailor;
import com.dressbookbackend.service.TailorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tailors")
public class TailorController {

    private final TailorService service;

    public TailorController(
            TailorService service) {

        this.service = service;
    }

    // A - CREATE

    @PostMapping("/save")
    public String save(
            @RequestBody Tailor tailor) {

        return service.save(tailor);
    }

    // A - READ ALL

    @GetMapping("/all")
    public List<Tailor> getAll() {

        return service.getAll();
    }

    // A - READ BY ID

    @GetMapping("/{id}")
    public Tailor getById(
            @PathVariable UUID id) {

        return service.getById(id).orElse(null);
    }

    // A - UPDATE

    @PutMapping("/update/{id}")
    public String update(
            @PathVariable UUID id,
            @RequestBody Tailor tailor) {

        return service.update(id, tailor);
    }

    // A - DELETE

    @DeleteMapping("/delete/{id}")
    public String delete(
            @PathVariable UUID id) {

        return service.delete(id);
    }

    // C1 - TAILORS BY GARMENT TYPE

    @GetMapping("/garment-type/{name}")
    public List<Tailor> findTailorsByGarmentType(
            @PathVariable String name) {

        return service.findTailorsByGarmentType(name);
    }

    // D3 - ADD GARMENT TYPE TO TAILOR

    @PostMapping("/{tailorId}/garment-type/{garmentTypeId}")
    public String addGarmentType(
            @PathVariable UUID tailorId,
            @PathVariable UUID garmentTypeId) {

        return service.addGarmentType(
                tailorId,
                garmentTypeId);
    }

    // D4 - CHANGE SEWING STATION

    @PutMapping("/{tailorId}/station/{stationNumber}")
    public String changeSewingStation(
            @PathVariable UUID tailorId,
            @PathVariable int stationNumber) {

        return service.changeSewingStation(
                tailorId,
                stationNumber);
    }
}