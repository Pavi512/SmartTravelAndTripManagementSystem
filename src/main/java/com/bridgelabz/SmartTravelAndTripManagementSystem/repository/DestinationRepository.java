package com.bridgelabz.SmartTravelAndTripManagementSystem.repository;

import com.bridgelabz.SmartTravelAndTripManagementSystem.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository
        extends JpaRepository<Destination, Long> {

}