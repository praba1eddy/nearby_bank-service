package com.nearbybank.api.controller;

import com.nearbybank.api.model.BankInfo;
import com.nearbybank.api.service.GoogleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banks")
public class BankController {

    @Autowired
    private GoogleService googleService;

    @GetMapping("/nearby")
    public List<BankInfo> getNearbyBanks(@RequestParam String zip) {
        return googleService.getNearbyBanks(zip);
    }
}
