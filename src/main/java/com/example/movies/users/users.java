package com.example.movies.users;

import com.example.movies.bookings.bookings;
import com.example.movies.comments.comments;
import com.example.movies.ratings.ratings;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "users")
public class users {
    @Id @GeneratedValue
    private long id;
    private String full_name;
    private String email;
    private String password;
    // relationship

    @OneToMany(
            mappedBy = "users",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<ratings> ratings;

    @OneToMany(mappedBy="users")
    private Set<comments> comments;

    @OneToMany(mappedBy="users")
    private Set<bookings> bookings;

    public users(){

    }
    public users(long id, String full_name, String email, String password) {
        this.id = id;
        this.full_name = full_name;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
