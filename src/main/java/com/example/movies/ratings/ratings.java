package com.example.movies.ratings;

import com.example.movies.Films.films;
import com.example.movies.users.users;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ratings")
public class ratings {
    @Id
    private long id;
    private int rate;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // relationship
    @ManyToOne
    @JoinColumn(name="users_id")
    private users users;

    @ManyToOne
    @JoinColumn(name="films_id")
    private films films;

    // constructor
    public ratings(long id, int rate, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.rate = rate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // getter and setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
