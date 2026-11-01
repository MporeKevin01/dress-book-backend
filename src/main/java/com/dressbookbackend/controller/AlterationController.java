package com.dressbookbackend.controller;

import com.dressbookbackend.entity.Alteration;
import com.dressbookbackend.service.AlterationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/alterations")
public class AlterationController {

    private final AlterationService service;

    public AlterationController(
            AlterationService service) {

        this.service = service;
    }

    // E1 - SAVE ALTERATION

    @PostMapping("/save/{orderId}")
    public String save(
            @PathVariable UUID orderId,
            @RequestBody Alteration alteration) {

        return service.save(
                orderId,
                alteration);
    }

    // E2 - ALTERATION HISTORY

    @GetMapping("/history/{phone}")
    public List<Alteration> history(
            @PathVariable String phone) {

        return service.history(phone);
    }
}