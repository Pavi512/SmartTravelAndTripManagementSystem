package com.bridgelabz.SmartTravelAndTripManagementSystem.serviceimpl;

import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.DestinationRequest;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.TravelPackageRequest;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.TravelPackageResponse;
import com.bridgelabz.SmartTravelAndTripManagementSystem.exception.ResourceNotFoundException;
import com.bridgelabz.SmartTravelAndTripManagementSystem.model.Destination;
import com.bridgelabz.SmartTravelAndTripManagementSystem.model.TravelPackage;
import com.bridgelabz.SmartTravelAndTripManagementSystem.repository.TravelPackageRepository;
import com.bridgelabz.SmartTravelAndTripManagementSystem.service.DestinationService;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.DestinationResponse;
import com.bridgelabz.SmartTravelAndTripManagementSystem.repository.DestinationRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;
    private final TravelPackageRepository travelPackageRepository;

    public DestinationServiceImpl(
            DestinationRepository destinationRepository,
            TravelPackageRepository travelPackageRepository) {

        this.destinationRepository = destinationRepository;
        this.travelPackageRepository = travelPackageRepository;
    }


    // 1. CREATE DESTINATION
    // POST /api/destinations


    @Override
    public DestinationResponse createDestination(
            DestinationRequest request) {

        Destination destination = new Destination();

        destination.setName(request.getName());
        destination.setCountry(request.getCountry());
        destination.setDescription(request.getDescription());

        Destination savedDestination =
                destinationRepository.save(destination);

        return convertToDestinationResponse(savedDestination);
    }


    // 2. GET ALL DESTINATIONS
    // GET /api/destinations


    @Override
    public List<DestinationResponse> getAllDestinations() {

        List<Destination> destinations =
                destinationRepository.findAll();

        return destinations.stream()
                .map(this::convertToDestinationResponse)
                .toList();
    }


    // 3. GET DESTINATION BY ID
    // GET /api/destinations/{id}

    @Override
    public DestinationResponse getDestinationById(Long id) {

        Destination destination =
                destinationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Destination not found with id: " + id
                                )
                        );

        return convertToDestinationResponse(destination);
    }



    // 4. ADD TRAVEL PACKAGE
    // POST /api/destinations/{id}/packages

    @Override
    public TravelPackageResponse addTravelPackage(
            Long destinationId,
            TravelPackageRequest request) {

        // First find the destination
        Destination destination =
                destinationRepository.findById(destinationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Destination not found with id: "
                                                + destinationId
                                )
                        );

        // Create TravelPackage entity
        TravelPackage travelPackage =
                new TravelPackage();

        travelPackage.setPackageName(
                request.getPackageName()
        );

        travelPackage.setPrice(
                request.getPrice()
        );

        travelPackage.setDuration(
                request.getDuration()
        );

        // Establish ManyToOne relationship
        travelPackage.setDestination(destination);

        // Save package
        TravelPackage savedPackage =
                travelPackageRepository.save(travelPackage);

        return convertToTravelPackageResponse(savedPackage);
    }


    // DTO CONVERSION METHODS


    private DestinationResponse convertToDestinationResponse(
            Destination destination) {

        return new DestinationResponse(
                destination.getId(),
                destination.getName(),
                destination.getCountry(),
                destination.getDescription()
        );
    }


    private TravelPackageResponse convertToTravelPackageResponse(
            TravelPackage travelPackage) {

        return new TravelPackageResponse(
                travelPackage.getId(),
                travelPackage.getPackageName(),
                travelPackage.getPrice(),
                travelPackage.getDuration(),
                travelPackage.getDestination().getId()
        );
    }


    // 5. GET DESTINATION PACKAGES
// GET /api/destinations/{id}/packages

    @Override
    public List<TravelPackageResponse> getDestinationPackages(
            Long destinationId) {

        // First check whether the destination exists
        destinationRepository.findById(destinationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Destination not found with id: "
                                        + destinationId
                        )
                );

        // Find all packages belonging to this destination
        List<TravelPackage> packages =
                travelPackageRepository.findByDestinationId(
                        destinationId
                );

        // Convert TravelPackage entities into response DTOs
        return packages.stream()
                .map(this::convertToTravelPackageResponse)
                .toList();
    }
}