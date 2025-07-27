package com.eren.CloudTrip.controller;


import com.eren.CloudTrip.service.JwtService;
import com.eren.CloudTrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/token")
    public String generateToken(@RequestParam String email , @RequestParam String password){

        if (userService.validateUser(email,password)){
            return jwtService.generateToken(email);
        }
        else{
            return "Credentials invalid";
        }


    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam String token){
        boolean isValid = jwtService.isTokenValid(token);

        return isValid?"Token is valid Email: " + jwtService.extractEmail(token) :"Token is not valid";

    }

}
