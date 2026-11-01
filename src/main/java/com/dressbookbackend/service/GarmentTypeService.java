package com.dressbookbackend.service;

import com.dressbookbackend.entity.GarmentType;
import com.dressbookbackend.repository.GarmentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GarmentTypeService {

    private final GarmentTypeRepository repository;

    public GarmentTypeService(
            GarmentTypeRepository repository) {

        this.repository = repository;
    }

    public String save(GarmentType garmentType) {

        repository.save(garmentType);

        return "Garment type saved successfully";
    }

    public List<GarmentType> getAll() {

        return repository.findAll();
    }

    public Optional<GarmentType> getById(UUID id) {

        return repository.findById(id);
    }

    public String update(
            UUID id,
            GarmentType garmentType) {

        GarmentType existing =
                repository.findById(id)
                        .orElse(null);

        if (existing == null) {
            return "Garment type not found";
        }

        existing.setName(garmentType.getName());

        repository.save(existing);

        return "Garment type updated successfully";
    }

    public String delete(UUID id) {

        if (!repository.existsById(id)) {
            return "Garment type not found";
        }

        repository.deleteById(id);

        return "Garment type deleted successfully";
    }

    // C3

    public List<GarmentType> unused() {

        return repository.unused();
    }
}