package com.eren.CloudTrip.test;


import com.eren.CloudTrip.model.Flight;
import com.eren.CloudTrip.model.User;
import com.eren.CloudTrip.repo.FlightRepository;
import com.eren.CloudTrip.repo.UserRepository;
import com.eren.CloudTrip.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = List.of(new User());
        when(userRepository.findAll()).thenReturn(users);

        ResponseEntity<List<User>> response = userService.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void testAddUser() {
        User user = new User();
        user.setPassword("plain");
        when(userRepository.save(any(User.class))).thenReturn(user);

        ResponseEntity<User> response = userService.addUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotEquals("plain", response.getBody().getPassword()); // Şifre encode edilmiş olmalı
    }

    @Test
    void testPurchaseFlight_Success() {
        User user = new User();
        user.setPurchasedFlights(new ArrayList<>());
        Flight flight = new Flight();

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(flightRepository.findById(2)).thenReturn(Optional.of(flight));
        when(userRepository.save(any(User.class))).thenReturn(user);

        ResponseEntity<String> response = userService.purchaseFlight(1, 2);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(user.getPurchasedFlights().contains(flight));
    }

    @Test
    void testPurchaseFlight_UserNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<String> response = userService.purchaseFlight(1, 2);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testPurchaseFlight_FlightNotFound() {
        User user = new User();
        user.setPurchasedFlights(new ArrayList<>());
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(flightRepository.findById(2)).thenReturn(Optional.empty());

        ResponseEntity<String> response = userService.purchaseFlight(1, 2);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
