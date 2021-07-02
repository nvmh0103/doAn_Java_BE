package com.example.movies.bookings_detail;


import com.example.movies.bookedSeat.bookedSeat;
import com.example.movies.seats.seats;
import com.example.movies.tickets.tickets;
import com.example.movies.users.users;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class booking_details {
    @Id @GeneratedValue
     private long id;
    // relasionship

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "tickets_id")
    private tickets tickets;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="bookedSeat_id")
    private bookedSeat bookedSeat;

    public booking_details(){

    }

    public booking_details(long id, com.example.movies.tickets.tickets tickets, com.example.movies.bookedSeat.bookedSeat bookedSeat) {
        this.id = id;
        this.tickets = tickets;
        this.bookedSeat = bookedSeat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public com.example.movies.tickets.tickets getTickets() {
        return tickets;
    }

    public void setTickets(com.example.movies.tickets.tickets tickets) {
        this.tickets = tickets;
    }

    public com.example.movies.bookedSeat.bookedSeat getBookedSeat() {
        return bookedSeat;
    }

    public void setBookedSeat(com.example.movies.bookedSeat.bookedSeat bookedSeat) {
        this.bookedSeat = bookedSeat;
    }
}
