package com.example.movies.schedules;

import com.example.movies.Films.films;
import com.example.movies.rooms.rooms;
import com.example.movies.tickets.tickets;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
public class schedules {
    @Id
    private long id;
    private Timestamp startAt;
    private Timestamp endAt;
    // relationship
    @ManyToOne
    @JoinColumn(name = "films_id")
    private films film;


    @ManyToOne
    @JoinColumn(name="rooms_id")
    private rooms rooms;


    @OneToMany(mappedBy = "schedules")
    private Set<tickets> tickets;

    public schedules(long id, Timestamp startAt, Timestamp endAt) {
        this.id = id;
        this.startAt = startAt;
        this.endAt = endAt;
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
}
