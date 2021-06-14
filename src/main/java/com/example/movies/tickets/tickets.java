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

    @OneToOne(mappedBy = "tickets")
    private booking_details booking_details;

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
}
