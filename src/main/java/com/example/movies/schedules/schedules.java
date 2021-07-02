package com.example.movies.schedules;

import com.example.movies.films.films;
import com.example.movies.bookedSeat.bookedSeat;
import com.example.movies.rooms.rooms;
import com.example.movies.tickets.tickets;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class schedules {
    @Id @GeneratedValue
    private long id;
    private String name;
    private Timestamp startAt;
    private Timestamp endAt;
    // relationship
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "films_id")
    private films film;

    @ManyToOne
    @JoinColumn(name="rooms_id")
    private rooms rooms;

    @JsonIgnore
    @OneToMany(mappedBy = "schedules")
    private List<tickets> tickets=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy="schedules")
    private List<bookedSeat> bookedSeats= new ArrayList<>();

    public schedules(){

    }

    public schedules(long id, String name, Timestamp startAt, Timestamp endAt, films film, com.example.movies.rooms.rooms rooms) {
        this.id = id;
        this.name=name;
        this.startAt = startAt;
        this.endAt = endAt;
        this.film = film;
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getStartAt() {
        return startAt;
    }

    public void setStartAt(Timestamp startAt) {
        this.startAt = startAt;
    }

    public Timestamp getEndAt() {
        return endAt;
    }

    public void setEndAt(Timestamp endAt) {
        this.endAt = endAt;
    }

    public films getFilm() {
        return film;
    }

    public void setFilm(films film) {
        this.film = film;
    }

    public com.example.movies.rooms.rooms getRooms() {
        return rooms;
    }

    public void setRooms(com.example.movies.rooms.rooms rooms) {
        this.rooms = rooms;
    }

    public List<com.example.movies.tickets.tickets> getTickets() {
        return tickets;
    }

    public void setTickets(List<com.example.movies.tickets.tickets> tickets) {
        this.tickets = tickets;
    }

    public List<bookedSeat> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<bookedSeat> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    @Override
    public String toString() {
        return "schedules{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startAt=" + startAt +
                ", endAt=" + endAt +
                ", film=" + film +
                ", rooms=" + rooms +
                ", tickets=" + tickets +
                '}';
    }
}
