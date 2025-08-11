package com.eren.CloudTrip.controller;


import com.eren.CloudTrip.service.JwtService;
import com.eren.CloudTrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
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

//    @PostMapping("/token")
//    public String generateToken(@RequestParam String email , @RequestParam String password){
//
//        if (userService.validateUser(email,password)){
//            return jwtService.generateToken(email);
//        }
//        else{
//            return "Credentials invalid";
//        }
//
//
//    }
    @PostMapping("/token")
    public ResponseEntity<String> generateToken(@RequestParam String email , @RequestParam String password){

        if (userService.validateUser(email,password)){
            String token = jwtService.generateToken(email);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credentials invalid");
        }
    }


    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam String token){
        boolean isValid = jwtService.isTokenValid(token);

        if (isValid) {
            return ResponseEntity.ok("Token is valid Email: " + jwtService.extractEmail(token));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Token is not valid");
        }
    }

}
