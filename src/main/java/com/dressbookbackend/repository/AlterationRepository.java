package com.dressbookbackend.repository;

import com.dressbookbackend.entity.Alteration;
import com.dressbookbackend.entity.TailoringOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AlterationRepository
        extends JpaRepository<Alteration, UUID> {

    // E2

    @Query("""
    SELECT a
    FROM Alteration a
    WHERE a.tailoringOrder.customer.phone = :phone
    ORDER BY a.requestedDate DESC
    """)
    List<Alteration> history(String phone);

    // E3

    boolean existsByTailoringOrder(
            TailoringOrder tailoringOrder);
}