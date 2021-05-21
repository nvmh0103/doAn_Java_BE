package com.example.movies.bookings;

import com.example.movies.bookings_detail.booking_details;
import com.example.movies.users.users;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
public class bookings {
    @Id
    private long id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    // relationships
    @ManyToOne
    @JoinColumn(name="users_id")
    private users users;

    @OneToMany(mappedBy = "bookings")
    private Set<booking_details> booking_details;

    // constructor


    public bookings(long id, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
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

    @Override
    public String toString() {
        return "bookings{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
