package com.eren.CloudTrip.controller;

import com.eren.CloudTrip.model.Flight;
import com.eren.CloudTrip.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    FlightService service;

    @GetMapping("/getAllFlights")
    public ResponseEntity<List<Flight>> getAllFlights(){
        return service.getAllFlights();
    }

    @PostMapping("/addFlight")
    public ResponseEntity<String> addFlight(@RequestBody Flight flight) {

        return service.save(flight);
    }

    @PostMapping("/addFlights")
    public ResponseEntity<String> addFlights(@RequestBody List<Flight> flights){
        return service.saveAll(flights);
    }

}
