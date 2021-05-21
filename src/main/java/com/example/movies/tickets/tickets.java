package com.example.movies.tickets;

import com.example.movies.bookings_detail.booking_details;
import com.example.movies.schedules.schedules;

import javax.persistence.*;
import java.util.Set;

@Entity
public class tickets {
    @Id
    private long id;
    private int price;
    private String type;
    //Relationship
    @ManyToOne
    @JoinColumn(name="schedules_id")
    private schedules schedules;

    @OneToMany(mappedBy = "tickets")
    private Set<booking_details> booking_details;
}
