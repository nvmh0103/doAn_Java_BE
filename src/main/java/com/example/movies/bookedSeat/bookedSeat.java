package com.example.movies.bookedSeat;

import com.example.movies.bookings_detail.booking_details;
import com.example.movies.schedules.schedules;
import com.example.movies.seats.seats;

import javax.persistence.*;

@Entity
public class bookedSeat {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="seats_id")
    private seats seats;

    @ManyToOne
    @JoinColumn(name="schedules_id")
    private schedules schedules;

    @OneToOne(mappedBy = "bookedSeat")
    private booking_details booking_details;

    public bookedSeat(){

    }
    public bookedSeat(long id, com.example.movies.seats.seats seats, com.example.movies.schedules.schedules schedules, com.example.movies.bookings_detail.booking_details booking_details) {
        this.id = id;
        this.seats = seats;
        this.schedules = schedules;
        this.booking_details = booking_details;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public com.example.movies.seats.seats getSeats() {
        return seats;
    }

    public void setSeats(com.example.movies.seats.seats seats) {
        this.seats = seats;
    }

    public com.example.movies.schedules.schedules getSchedules() {
        return schedules;
    }

    public void setSchedules(com.example.movies.schedules.schedules schedules) {
        this.schedules = schedules;
    }

    public com.example.movies.bookings_detail.booking_details getBooking_details() {
        return booking_details;
    }

    public void setBooking_details(com.example.movies.bookings_detail.booking_details booking_details) {
        this.booking_details = booking_details;
    }
}
