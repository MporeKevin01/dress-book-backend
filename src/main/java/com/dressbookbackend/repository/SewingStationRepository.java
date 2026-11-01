package com.dressbookbackend.repository;

import com.dressbookbackend.entity.SewingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SewingStationRepository
        extends JpaRepository<SewingStation, UUID> {

    // C2

    @Query("""
    SELECT s
    FROM SewingStation s
    WHERE NOT EXISTS
    (
        SELECT t
        FROM Tailor t
        WHERE t.sewingStation = s
    )
    ORDER BY s.stationNumber
    """)
    List<SewingStation> freeStations();

    // D4

    Optional<SewingStation>
    findByStationNumber(int stationNumber);
}