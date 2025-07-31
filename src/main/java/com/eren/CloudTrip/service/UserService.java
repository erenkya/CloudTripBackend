package com.eren.CloudTrip.service;

import com.eren.CloudTrip.model.Flight;
import com.eren.CloudTrip.model.User;
import com.eren.CloudTrip.repo.FlightRepository;
import com.eren.CloudTrip.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repo;

    @Autowired
    FlightRepository flightRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST );


    }

    public ResponseEntity<User> getUser(int id) {
        try {
            return repo.findById(id)
                    .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new User(), HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new User(), HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<User>  addUser(User user) {
        try {
            User setUser = user;
            setUser.setPassword(encoder.encode(user.getPassword()));
            repo.save(setUser);
            return new ResponseEntity<> (setUser , HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<> (user , HttpStatus.BAD_REQUEST);

        }
    }

    public ResponseEntity<String> purchaseFlight(int userId, int flightId ) {
        try {
            Optional<User> userOptional = repo.findById(userId);
            if(userOptional.isEmpty()) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
            User user = userOptional.get();

            Optional<Flight> flightOptional =flightRepository.findById(flightId);
            if(flightOptional.isEmpty()){
                return new ResponseEntity<>("Flight not found" , HttpStatus.NOT_FOUND);
            }
            Flight flight = flightOptional.get();

            if (flight.getSeatCapacity() <= 0) {
                return new ResponseEntity<>("No available seats for this flight", HttpStatus.BAD_REQUEST);
            }

            // Seat capacity azalt
            flight.setSeatCapacity(flight.getSeatCapacity() - 1);
            flightRepository.save(flight);

            // Uçuşu kullanıcıya ekle
            user.getPurchasedFlights().add(flight);
            repo.save(user);

            return new ResponseEntity<>("UserID: " + user.getId() + " FlightID: " + flight.getId() + " purchase has been made" , HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed" , HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> cancelFlight(int userId, int flightId) {
        try {
            Optional<User> userOptional = repo.findById(userId);
            if(userOptional.isEmpty()) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
            User user = userOptional.get();

            Optional<Flight> flightOptional =flightRepository.findById(flightId);
            if(flightOptional.isEmpty()){
                return new ResponseEntity<>("Flight not found" , HttpStatus.NOT_FOUND);
            }
            Flight flight = flightOptional.get();

            boolean flightChecker = false;
            for(Flight listElement: user.getPurchasedFlights()){
                if(listElement.getId() == flightId)
                    flightChecker = true;
            }
            if (!flightChecker){
                return new ResponseEntity<>("There is no purchased flight with flightID:"+flightId+" for user with userID:" + userId , HttpStatus.NOT_FOUND);
            }

            // Seat capacity arttır
            flight.setSeatCapacity(flight.getSeatCapacity() + 1);
            flightRepository.save(flight);

            // Uçuşu kullanıcıdan çıkar
            user.getPurchasedFlights().remove(flight);
            repo.save(user);

            return new ResponseEntity<>("UserID: " + user.getId() + " FlightID: " + flight.getId() + " cancelation has been made" , HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed" , HttpStatus.BAD_REQUEST);
        }
    }

    public boolean validateUser(String email, String password) {
        Optional<User> userOptional = repo.findByEmail(email);
        if(userOptional.isEmpty()) {
            return false;
        }
        User user = userOptional.get();
        return encoder.matches(password , user.getPassword());

    }
}
