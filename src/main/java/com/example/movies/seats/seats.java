package com.example.movies.seats;

import com.example.movies.bookedSeat.bookedSeat;
import com.example.movies.bookings_detail.booking_details;
import com.example.movies.rooms.rooms;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class seats {
    @Id @GeneratedValue
    private long id;
    private String name;
    private String kind;
    private int basePrice;

    // relationship

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "rooms_id")
    private rooms rooms;

    @OneToMany(mappedBy = "seats")
    private List<bookedSeat> bookedSeats=new ArrayList<>();

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

    public com.example.movies.rooms.rooms getRooms() {
        return rooms;
    }

    public void setRooms(com.example.movies.rooms.rooms rooms) {
        this.rooms = rooms;
    }

    public List<bookedSeat> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<bookedSeat> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    @Override
    public String toString() {
        return "seats{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", kind='" + kind + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}
