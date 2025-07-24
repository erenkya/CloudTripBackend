package com.eren.CloudTrip.repo;

import com.eren.CloudTrip.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
}