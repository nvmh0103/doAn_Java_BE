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
    // relationship
    @OneToMany(mappedBy = "rooms")
    private Set<seats> seats;

    @OneToMany(mappedBy= "rooms")
    private Set<schedules> schedule;

    public rooms(){

    }

    public rooms(long id, String name) {
        this.id = id;
        this.name = name;
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

}
