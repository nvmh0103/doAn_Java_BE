package com.example.movies.Films;

import com.example.movies.comments.comments;
import com.example.movies.ratings.ratings;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "films")
public class films {
    @Id @GeneratedValue
    private long id;
    private String name;
    private String actor;
    private String director;
    private int duration;
    private String description;
    private String country;
    private String pictureLink;


    // relationship

    @OneToMany(mappedBy = "films")
    private Set<ratings> ratings;

    @OneToMany(mappedBy="films")
    private Set<comments> comments;

    public films(){

    }

    public films(long id, String name, String actor, String director, int duration, String description, String country, String pictureLink) {
        this.id = id;
        this.name = name;
        this.actor = actor;
        this.director = director;
        this.duration = duration;
        this.description = description;
        this.country = country;
        this.pictureLink = pictureLink;
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

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }
}
