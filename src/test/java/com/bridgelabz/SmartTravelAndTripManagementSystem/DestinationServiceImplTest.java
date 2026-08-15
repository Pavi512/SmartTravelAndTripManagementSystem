package com.bridgelabz.SmartTravelAndTripManagementSystem;


import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.DestinationRequest;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.DestinationResponse;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.TravelPackageResponse;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.TravelPackageRequest;
import com.bridgelabz.SmartTravelAndTripManagementSystem.service.DestinationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


// Class to test Destination service implementation
@SpringBootTest
public class DestinationServiceImplTest {

    // Field injection
    @Autowired
    private DestinationService destinationService;


    // =========================================================
    // TEST 1: CREATE DESTINATION
    // POST /api/destinations
    // =========================================================

    @Test
    public void createDestination() {

        // Creates destination request object
        DestinationRequest destination = new DestinationRequest();

        destination.setName("Goa");
        destination.setCountry("India");
        destination.setDescription(
                "Beautiful beaches and nightlife"
        );

        // Creates the destination
        DestinationResponse saved =
                destinationService.createDestination(destination);

        // Verifies that destination was created
        assertNotNull(saved);
        assertNotNull(saved.getId());

        assertEquals("Goa", saved.getName());
        assertEquals("India", saved.getCountry());
        assertEquals(
                "Beautiful beaches and nightlife",
                saved.getDescription()
        );
    }


    // =========================================================
    // TEST 2: GET ALL DESTINATIONS
    // GET /api/destinations
    // =========================================================

    @Test
    public void getAllDestinations() {

        // Creates first destination
        DestinationRequest destination1 =
                new DestinationRequest();

        destination1.setName("Goa");
        destination1.setCountry("India");
        destination1.setDescription(
                "Beautiful beaches"
        );


        // Creates second destination
        DestinationRequest destination2 =
                new DestinationRequest();

        destination2.setName("Paris");
        destination2.setCountry("France");
        destination2.setDescription(
                "City of lights"
        );


        // Saves both destinations
        destinationService.createDestination(destination1);
        destinationService.createDestination(destination2);


        // Retrieves all destinations
        List<DestinationResponse> destinations =
                destinationService.getAllDestinations();


        // Verifies that destinations are returned
        assertNotNull(destinations);

        assertTrue(
                destinations.size() >= 2
        );
    }


    // =========================================================
    // TEST 3: GET DESTINATION BY ID
    // GET /api/destinations/{id}
    // =========================================================

    @Test
    public void getDestinationById() {

        // Creates a destination
        DestinationRequest destination =
                new DestinationRequest();

        destination.setName("Dubai");
        destination.setCountry("UAE");
        destination.setDescription(
                "Luxury destination"
        );


        // Saves destination and gets generated ID
        DestinationResponse saved =
                destinationService.createDestination(destination);


        // Retrieves destination using ID
        DestinationResponse found =
                destinationService.getDestinationById(
                        saved.getId()
                );


        // Verifies that correct destination is returned
        assertNotNull(found);

        assertEquals(
                saved.getId(),
                found.getId()
        );

        assertEquals(
                "Dubai",
                found.getName()
        );

        assertEquals(
                "UAE",
                found.getCountry()
        );

        assertEquals(
                "Luxury destination",
                found.getDescription()
        );
    }


    // =========================================================
    // TEST 4: ADD TRAVEL PACKAGE
    // POST /api/destinations/{id}/packages
    // =========================================================

    @Test
    public void addTravelPackage() {

        // Creates a destination
        DestinationRequest destination =
                new DestinationRequest();

        destination.setName("Goa");
        destination.setCountry("India");
        destination.setDescription(
                "Beautiful beaches"
        );


        // Saves destination
        DestinationResponse savedDestination =
                destinationService.createDestination(
                        destination
                );


        // Creates travel package request
        TravelPackageRequest travelPackage =
                new TravelPackageRequest();

        travelPackage.setPackageName(
                "Goa Beach Escape"
        );

        travelPackage.setPrice(
                15000.0
        );

        travelPackage.setDuration(
                4
        );


        // Adds package to destination
        TravelPackageResponse savedPackage =
                destinationService.addTravelPackage(
                        savedDestination.getId(),
                        travelPackage
                );


        // Verifies that package was created
        assertNotNull(savedPackage);

        assertNotNull(
                savedPackage.getId()
        );

        assertEquals(
                "Goa Beach Escape",
                savedPackage.getPackageName()
        );

        assertEquals(
                15000.0,
                savedPackage.getPrice()
        );

        assertEquals(
                4,
                savedPackage.getDuration()
        );

        // Verifies that package belongs to correct destination
        assertEquals(
                savedDestination.getId(),
                savedPackage.getDestinationId()
        );
    }
}
