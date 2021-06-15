package com.example.movies.rooms;

import com.example.movies.schedules.schedules;
import com.example.movies.seats.seats;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class rooms {
    @Id @GeneratedValue
    private long id;
    private String name;
    // relationship
    @JsonIgnore
    @OneToMany(mappedBy = "rooms",cascade = CascadeType.ALL)
    private List<seats> seats=new ArrayList<>();

    @OneToMany(mappedBy= "rooms")
    private List<schedules> schedule=new ArrayList<>();

    public rooms(){

    }

    public rooms(long id, String name,List<seats> seats) {
        this.id = id;
        this.name = name;
        this.seats=seats;
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

    public List<com.example.movies.seats.seats> getSeats() {
        return seats;
    }

    public void setSeats(List<com.example.movies.seats.seats> seats) {
        this.seats = seats;
    }

    public List<schedules> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<schedules> schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "rooms{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seats=" + seats.toString() +
                ", schedule=" + schedule +
                '}';
    }
}
