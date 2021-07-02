package com.example.movies.tickets;

import com.example.movies.bookings_detail.booking_details;
import com.example.movies.schedules.schedules;
import com.example.movies.users.users;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class tickets {
    @Id @GeneratedValue
    private long id;
    private int price;
    private String type;
    //Relationship
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="schedules_id")
    private schedules schedules;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="users_id")
    private users users;

    @JsonIgnore
    @OneToOne(mappedBy = "tickets")
    private booking_details booking_details;

    public tickets(){

    }
    public tickets(long id, int price, String type) {
        this.id = id;
        this.price = price;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public users getUser() {
        return users;
    }

    public void setUser(users users) {
        this.users = users;
    }
}
