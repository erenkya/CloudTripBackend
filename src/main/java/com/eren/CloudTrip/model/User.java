package com.eren.CloudTrip.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String role;

    @ManyToMany
    private List<Flight> purchasedFlights;



    public User() {
    }

    public User(int id, List<Flight> purchasedFlights, String surname, String name, String password, String email) {
        this.id = id;
        this.purchasedFlights = purchasedFlights;
        this.surname = surname;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Flight> getPurchasedFlights() {
        return purchasedFlights;
    }

    public void setPurchasedFlights(List<Flight> purchasedFlights) {
        this.purchasedFlights = purchasedFlights;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
