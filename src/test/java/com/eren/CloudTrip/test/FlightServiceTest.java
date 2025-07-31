package com.eren.CloudTrip.test;



import com.eren.CloudTrip.controller.FlightController;
import com.eren.CloudTrip.model.Flight;
import com.eren.CloudTrip.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightServiceTest {

    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightController flightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFlights() {
        List<Flight> mockFlights = List.of(new Flight());
        when(flightService.getAllFlights()).thenReturn(ResponseEntity.ok(mockFlights));

        ResponseEntity<List<Flight>> response = flightController.getAllFlights();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockFlights, response.getBody());
        verify(flightService, times(1)).getAllFlights();
    }

    @Test
    void testAddFlight() {
        Flight flight = new Flight();
        when(flightService.save(flight)).thenReturn(ResponseEntity.ok("Flight saved"));

        ResponseEntity<String> response = flightController.addFlight(flight);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Flight saved", response.getBody());
        verify(flightService, times(1)).save(flight);
    }

    @Test
    void testAddFlights() {
        List<Flight> flights = List.of(new Flight(), new Flight());
        when(flightService.saveAll(flights)).thenReturn(ResponseEntity.ok("Flights saved"));

        ResponseEntity<String> response = flightController.addFlights(flights);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Flights saved", response.getBody());
        verify(flightService, times(1)).saveAll(flights);
    }
}
