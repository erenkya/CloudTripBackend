package com.eren.CloudTrip.service;


import com.eren.CloudTrip.model.Flight;
import com.eren.CloudTrip.repo.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    FlightRepository repo;


    public void save(Flight flight) {
        repo.save(flight);
    }

    public void saveAll(List<Flight> flights) {
        repo.saveAll(flights);
    }

    public List<Flight> getAllFlights() {
        return repo.findAll();
    }
}
