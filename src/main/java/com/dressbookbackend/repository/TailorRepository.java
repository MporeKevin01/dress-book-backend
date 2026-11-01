package com.dressbookbackend.repository;

import com.dressbookbackend.entity.SewingStation;
import com.dressbookbackend.entity.Tailor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TailorRepository
        extends JpaRepository<Tailor, UUID> {

    // C1

    @Query("""
    SELECT t
    FROM Tailor t
    JOIN t.garmentTypes g
    WHERE LOWER(g.name)=LOWER(:name)
    """)
    List<Tailor> findTailorsByGarmentType(
            String name);

    // D4

    boolean existsBySewingStation(
            SewingStation sewingStation);
}