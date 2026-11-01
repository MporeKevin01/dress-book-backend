package com.dressbookbackend.repository;

import com.dressbookbackend.entity.GarmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


public interface GarmentTypeRepository
        extends JpaRepository<GarmentType, UUID> {

    // C3

    @Query("""
    SELECT g
    FROM GarmentType g
    WHERE g.tailors IS EMPTY
    """)
    List<GarmentType> unused();
}