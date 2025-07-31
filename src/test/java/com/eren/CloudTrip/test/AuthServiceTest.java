package com.eren.CloudTrip.test;


import com.eren.CloudTrip.controller.AuthController;
import com.eren.CloudTrip.service.JwtService;
import com.eren.CloudTrip.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateToken_Success() {
        String email = "test@example.com";
        String password = "1234";
        String token = "jwt-token";

        when(userService.validateUser(email, password)).thenReturn(true);
        when(jwtService.generateToken(email)).thenReturn(token);

        String result = authController.generateToken(email, password);

        assertEquals(token, result);
        verify(userService).validateUser(email, password);
        verify(jwtService).generateToken(email);
    }

    @Test
    void testGenerateToken_Failure() {
        String email = "test@example.com";
        String password = "wrongpass";

        when(userService.validateUser(email, password)).thenReturn(false);

        String result = authController.generateToken(email, password);

        assertEquals("Credentials invalid", result);
        verify(userService).validateUser(email, password);
        verify(jwtService, never()).generateToken(anyString());
    }

    @Test
    void testValidateToken_Valid() {
        String token = "valid-token";
        String email = "test@example.com";

        when(jwtService.isTokenValid(token)).thenReturn(true);
        when(jwtService.extractEmail(token)).thenReturn(email);

        String result = authController.validateToken(token);

        assertEquals("Token is valid Email: " + email, result);
        verify(jwtService).isTokenValid(token);
        verify(jwtService).extractEmail(token);
    }

    @Test
    void testValidateToken_Invalid() {
        String token = "invalid-token";

        when(jwtService.isTokenValid(token)).thenReturn(false);

        String result = authController.validateToken(token);

        assertEquals("Token is not valid", result);
        verify(jwtService).isTokenValid(token);
        verify(jwtService, never()).extractEmail(anyString());
    }
}
