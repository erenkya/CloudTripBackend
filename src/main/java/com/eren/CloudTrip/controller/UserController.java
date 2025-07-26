package com.eren.CloudTrip.controller;

import com.eren.CloudTrip.model.Flight;
import com.eren.CloudTrip.model.User;
import com.eren.CloudTrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;



    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        return service.getAllUsers();
    }
    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        return service.getUser(id);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return service.addUser(user);
    }
    @PostMapping("/purchaseFlight/{userId}/{flightId}")
    public ResponseEntity<String> purchaseFlight(@PathVariable int userId , @PathVariable int flightId ){
        return service.purchaseFlight(userId,flightId);
    }




}
