package com.example.movies.seats;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class seatsController {
    @Autowired
    private seatsRepository SeatsRepository;
}
