package com.eren.CloudTrip.controller;

import com.eren.CloudTrip.model.Flight;
import com.eren.CloudTrip.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    FlightService service;

    @GetMapping("/getAllFlights")
    public List<Flight> getAllFlights(){
        return service.getAllFlights();
    }

    @PostMapping("/addFlight")
    public String addFlight(@RequestBody Flight flight){
        service.save(flight);
        return "Success";
    }

    @PostMapping("/addFlights")
    public String addFlights(@RequestBody List<Flight> flights){
        service.saveAll(flights);
        return "Success";
    }

}
