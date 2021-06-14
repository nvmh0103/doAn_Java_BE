package com.example.movies.bookings_detail;


import com.example.movies.bookedSeat.bookedSeat;
import com.example.movies.seats.seats;
import com.example.movies.tickets.tickets;
import com.example.movies.users.users;

import javax.persistence.*;

@Entity
public class booking_details {
    @Id
     private long id;
    // relasionship

    @OneToOne
    @JoinColumn(name = "tickets_id")
    private tickets tickets;

    @ManyToOne
    @JoinColumn(name="user_id")
    private users users;

    @OneToOne
    @JoinColumn(name="bookedSeat_id")
    private bookedSeat bookedSeat;


}
