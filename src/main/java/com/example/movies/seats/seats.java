package com.example.movies.seats;

import com.example.movies.bookings_detail.booking_details;
import com.example.movies.rooms.rooms;

import javax.persistence.*;
import java.util.Set;

@Entity
public class seats {
    @Id
    private long id;
    private String name;
    private String kind;
    private int basePrice;

    // relationship

    @ManyToOne
    @JoinColumn(name = "rooms_id")
    private rooms rooms;

    public seats(){

    }
    public seats(long id, String name, String kind, int basePrice) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.basePrice = basePrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }


}
