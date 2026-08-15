package com.bridgelabz.SmartTravelAndTripManagementSystem.service;

import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.TravelPackageResponse;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.TravelPackageRequest;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.DestinationResponse;
import com.bridgelabz.SmartTravelAndTripManagementSystem.dto.DestinationRequest;


import java.util.List;

public interface DestinationService {

    DestinationResponse createDestination(
            DestinationRequest request);

    List<DestinationResponse> getAllDestinations();

    DestinationResponse getDestinationById(Long id);

    TravelPackageResponse addTravelPackage(
            Long destinationId,
            TravelPackageRequest request);

    List<TravelPackageResponse> getDestinationPackages(Long destinationId);
}