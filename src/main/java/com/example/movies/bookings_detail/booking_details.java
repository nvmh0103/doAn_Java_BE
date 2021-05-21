package com.example.movies.bookings_detail;

import com.example.movies.bookings.bookings;
import com.example.movies.seats.seats;
import com.example.movies.tickets.tickets;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class booking_details {
    @Id
     private long id;
    // relasionship
    @ManyToOne
    @JoinColumn(name= "seats_id")
    private seats seats;

    @ManyToOne
    @JoinColumn(name = "tickets_id")
    private tickets tickets;

    @ManyToOne
    @JoinColumn(name= "bookings_id")
    private bookings bookings;
}
