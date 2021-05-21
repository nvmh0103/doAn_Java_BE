package com.example.movies.rooms;

import com.example.movies.schedules.schedules;
import com.example.movies.seats.seats;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class rooms {
    @Id
    private long id;
    private String name;
    private byte status;
    // relationship
    @OneToMany(mappedBy = "rooms")
    private Set<seats> seats;

//    @OneToMany(mappedBy= "rooms")
//    private Set<schedules> schedule;
//
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
}
