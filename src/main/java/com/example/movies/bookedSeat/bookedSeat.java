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
}
