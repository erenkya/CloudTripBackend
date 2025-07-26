package com.eren.CloudTrip.service;


import com.eren.CloudTrip.model.Flight;
import com.eren.CloudTrip.repo.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {
    @Autowired
    FlightRepository repo;


    public ResponseEntity<String> save(Flight flight) {
        try{
            repo.save(flight);
            return new ResponseEntity<>("Flight Id:"+flight.getId()+" Saved Successfully", HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(),HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> saveAll(List<Flight> flights) {
        try{
            repo.saveAll(flights);
            return new ResponseEntity<>("Flights Saved Successfully", HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(),HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Flight>> getAllFlights() {
        try{
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }
}
