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
    private byte status;
    // relationship
    @OneToMany(mappedBy="seats")
    private Set<booking_details> booking_details;

    @ManyToOne
    @JoinColumn(name = "rooms_id")
    private rooms rooms;


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

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "seats{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
