package com.dressbookbackend.service;

import com.dressbookbackend.entity.Alteration;
import com.dressbookbackend.entity.TailoringOrder;
import com.dressbookbackend.enums.TailoringStatus;
import com.dressbookbackend.repository.AlterationRepository;
import com.dressbookbackend.repository.TailoringOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AlterationService {

    private final AlterationRepository alterationRepository;
    private final TailoringOrderRepository tailoringOrderRepository;

    public AlterationService(
            AlterationRepository alterationRepository,
            TailoringOrderRepository tailoringOrderRepository) {

        this.alterationRepository = alterationRepository;
        this.tailoringOrderRepository = tailoringOrderRepository;
    }

    // E1

    public String save(
            UUID orderId,
            Alteration alteration) {

        TailoringOrder order =
                tailoringOrderRepository.findById(orderId)
                        .orElse(null);

        if (order == null) {
            return "Tailoring order not found";
        }

        if (order.getStatus() != TailoringStatus.READY) {
            return "Alterations are only for READY tailoring orders";
        }

        alteration.setRequestedDate(LocalDate.now());
        alteration.setTailoringOrder(order);

        alterationRepository.save(alteration);

        return "Alteration saved successfully";
    }

    // E2

    public List<Alteration> history(String phone) {

        return alterationRepository.history(phone);
    }
}