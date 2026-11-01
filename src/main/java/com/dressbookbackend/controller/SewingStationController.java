package com.dressbookbackend.controller;

import com.dressbookbackend.entity.SewingStation;
import com.dressbookbackend.service.SewingStationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sewing-stations")
public class SewingStationController {

    private final SewingStationService service;

    public SewingStationController(
            SewingStationService service) {

        this.service = service;
    }

    // A - CREATE

    @PostMapping("/save")
    public String save(
            @RequestBody SewingStation station) {

        return service.save(station);
    }

    // A - READ ALL

    @GetMapping("/all")
    public List<SewingStation> getAll() {

        return service.getAll();
    }

    // A - READ BY ID

    @GetMapping("/{id}")
    public SewingStation getById(
            @PathVariable UUID id) {

        return service.getById(id).orElse(null);
    }

    // A - UPDATE

    @PutMapping("/update/{id}")
    public String update(
            @PathVariable UUID id,
            @RequestBody SewingStation station) {

        return service.update(id, station);
    }

    // A - DELETE

    @DeleteMapping("/delete/{id}")
    public String delete(
            @PathVariable UUID id) {

        return service.delete(id);
    }

    // C2 - FREE SEWING STATIONS

    @GetMapping("/free")
    public List<SewingStation> freeStations() {

        return service.freeStations();
    }
}