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
    private String fullName;
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

    public users(long id, String fullName, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
