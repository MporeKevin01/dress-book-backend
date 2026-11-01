package com.dressbookbackend.service;

import com.dressbookbackend.entity.SewingStation;
import com.dressbookbackend.repository.SewingStationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SewingStationService {

    private final SewingStationRepository repository;

    public SewingStationService(
            SewingStationRepository repository) {

        this.repository = repository;
    }

    public String save(SewingStation station) {

        repository.save(station);

        return "Sewing station saved successfully";
    }

    public List<SewingStation> getAll() {

        return repository.findAll();
    }

    public Optional<SewingStation> getById(UUID id) {

        return repository.findById(id);
    }

    public String update(
            UUID id,
            SewingStation station) {

        SewingStation existing =
                repository.findById(id)
                        .orElse(null);

        if (existing == null) {
            return "Sewing station not found";
        }

        existing.setName(station.getName());
        existing.setStationNumber(
                station.getStationNumber());

        repository.save(existing);

        return "Sewing station updated successfully";
    }

    public String delete(UUID id) {

        if (!repository.existsById(id)) {
            return "Sewing station not found";
        }

        repository.deleteById(id);

        return "Sewing station deleted successfully";
    }

    // C2

    public List<SewingStation> freeStations() {

        return repository.freeStations();
    }
}
