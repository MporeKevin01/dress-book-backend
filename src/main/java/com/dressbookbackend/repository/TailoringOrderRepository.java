package com.dressbookbackend.repository;

import com.dressbookbackend.entity.Customer;
import com.dressbookbackend.entity.Tailor;
import com.dressbookbackend.entity.TailoringOrder;
import com.dressbookbackend.enums.TailoringStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TailoringOrderRepository
        extends JpaRepository<TailoringOrder, UUID> {

    // B2 - Orders By Status

    List<TailoringOrder> findByStatusOrderByDueDateAsc(
            TailoringStatus status);

    // B3 - Orders Between Dates

    List<TailoringOrder> findByDueDateBetweenOrderByDueDate(
            LocalDate start,
            LocalDate end);

    // C4 - Customers Of Tailor

    @Query("""
    SELECT DISTINCT o.customer
    FROM TailoringOrder o
    WHERE o.tailor.id = :tailorId
    """)
    List<Customer> customersOfTailor(
            UUID tailorId);

    // D1 - Validation

    boolean existsByTailorAndDueDateAndStatusNot(
            Tailor tailor,
            LocalDate dueDate,
            TailoringStatus status);

    // F1 - Orders Per Status

    @Query("""
    SELECT o.status, COUNT(o)
    FROM TailoringOrder o
    GROUP BY o.status
    """)
    List<Object[]> countOrdersByStatus();

    // F2 - Tailor Absent

    @Modifying
    @Transactional
    @Query("""
    UPDATE TailoringOrder o
    SET o.status = com.dressbookbackend.enums.TailoringStatus.CANCELLED
    WHERE o.tailor.id = :tailorId
    AND o.dueDate = :date
    AND o.status <> com.dressbookbackend.enums.TailoringStatus.READY
    """)
    int cancelOrdersForTailorOnDate(
            UUID tailorId,
            LocalDate date);
}