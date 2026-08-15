package com.bridgelabz.SmartTravelAndTripManagementSystem.controller;

import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.DestinationResponse;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.DestinationRequest;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.TravelPackageRequest;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.TravelPackageResponse;
import com.bridgelabz.SmartTravelAndTripManagementSystem.service.DestinationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(
            DestinationService destinationService) {

        this.destinationService = destinationService;
    }



    // 1. CREATE DESTINATION
    // POST /api/destinations


    @PostMapping
    public ResponseEntity<DestinationResponse> createDestination(
            @Valid @RequestBody DestinationRequest request) {

        DestinationResponse response =
                destinationService.createDestination(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }



    // 2. GET ALL DESTINATIONS
    // GET /api/destinations


    @GetMapping
    public ResponseEntity<List<DestinationResponse>>
    getAllDestinations() {

        List<DestinationResponse> responses =
                destinationService.getAllDestinations();

        return ResponseEntity.ok(responses);
    }



    // 3. GET DESTINATION BY ID
    // GET /api/destinations/{id}


    @GetMapping("/{id}")
    public ResponseEntity<DestinationResponse>
    getDestinationById(
            @PathVariable Long id) {

        DestinationResponse response =
                destinationService.getDestinationById(id);

        return ResponseEntity.ok(response);
    }



    // 4. ADD TRAVEL PACKAGE
    // POST /api/destinations/{id}/packages


    @PostMapping("/{id}/packages")
    public ResponseEntity<TravelPackageResponse>
    addTravelPackage(
            @PathVariable Long id,
            @Valid @RequestBody TravelPackageRequest request) {

        TravelPackageResponse response =
                destinationService.addTravelPackage(
                        id,
                        request
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // 5. GET DESTINATION PACKAGES
// GET /api/destinations/{id}/packages

    @GetMapping("/{id}/packages")
    public ResponseEntity<List<TravelPackageResponse>>
    getDestinationPackages(
            @PathVariable Long id) {

        List<TravelPackageResponse> responses =
                destinationService.getDestinationPackages(id);

        return ResponseEntity.ok(responses);
    }

}