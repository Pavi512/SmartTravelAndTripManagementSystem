package com.bridgelabz.SmartTravelAndTripManagementSystem.repository;

import com.bridgelabz.SmartTravelAndTripManagementSystem.model.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelPackageRepository
        extends JpaRepository<TravelPackage, Long> {

    List<TravelPackage> findByDestinationId(Long destinationId);
}