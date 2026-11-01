package com.dressbookbackend.service;

import com.dressbookbackend.entity.GarmentType;
import com.dressbookbackend.entity.SewingStation;
import com.dressbookbackend.entity.Tailor;
import com.dressbookbackend.repository.GarmentTypeRepository;
import com.dressbookbackend.repository.SewingStationRepository;
import com.dressbookbackend.repository.TailorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TailorService {

    private final TailorRepository tailorRepository;
    private final GarmentTypeRepository garmentTypeRepository;
    private final SewingStationRepository sewingStationRepository;

    public TailorService(
            TailorRepository tailorRepository,
            GarmentTypeRepository garmentTypeRepository,
            SewingStationRepository sewingStationRepository) {

        this.tailorRepository = tailorRepository;
        this.garmentTypeRepository = garmentTypeRepository;
        this.sewingStationRepository = sewingStationRepository;
    }

    public String save(Tailor tailor) {

        tailorRepository.save(tailor);

        return "Tailor saved successfully";
    }

    public List<Tailor> getAll() {

        return tailorRepository.findAll();
    }

    public Optional<Tailor> getById(UUID id) {

        return tailorRepository.findById(id);
    }

    public String update(UUID id, Tailor tailor) {

        Tailor existing =
                tailorRepository.findById(id)
                        .orElse(null);

        if (existing == null) {
            return "Tailor not found";
        }

        existing.setFirstName(tailor.getFirstName());
        existing.setLastName(tailor.getLastName());
        existing.setPhone(tailor.getPhone());

        tailorRepository.save(existing);

        return "Tailor updated successfully";
    }

    public String delete(UUID id) {

        if (!tailorRepository.existsById(id)) {
            return "Tailor not found";
        }

        tailorRepository.deleteById(id);

        return "Tailor deleted successfully";
    }

    // C1

    public List<Tailor> findTailorsByGarmentType(
            String name) {

        return tailorRepository
                .findTailorsByGarmentType(name);
    }

    // D3

    public String addGarmentType(
            UUID tailorId,
            UUID garmentTypeId) {

        Tailor tailor =
                tailorRepository.findById(tailorId)
                        .orElse(null);

        GarmentType garmentType =
                garmentTypeRepository.findById(garmentTypeId)
                        .orElse(null);

        if (tailor == null || garmentType == null) {
            return "Tailor or garment type not found";
        }

        tailor.getGarmentTypes().add(garmentType);

        tailorRepository.save(tailor);

        return "Garment type added to the tailor";
    }

    // D4

    public String changeSewingStation(
            UUID tailorId,
            int stationNumber) {

        Tailor tailor =
                tailorRepository.findById(tailorId)
                        .orElse(null);

        if (tailor == null) {
            return "Tailor not found";
        }

        SewingStation station =
                sewingStationRepository
                        .findByStationNumber(stationNumber)
                        .orElse(null);

        if (station == null) {
            return "Sewing station not found";
        }

        if (tailorRepository
                .existsBySewingStation(station)) {

            return "That sewing station is taken by another tailor";
        }

        tailor.setSewingStation(station);

        tailorRepository.save(tailor);

        return "Sewing station changed successfully";
    }
}