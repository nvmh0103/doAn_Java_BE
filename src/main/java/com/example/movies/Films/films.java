package com.example.movies.Films;

import com.example.movies.comments.comments;
import com.example.movies.ratings.ratings;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "films")
public class films {
    @Id
    private long id;
    private String name;
    private String actor;
    private String producer;
    private String director;
    private int duration;
    private String description;
    private String country;
    private Timestamp createdAt;
    private Timestamp upadtedAt;

    // relationship

    @OneToMany(mappedBy = "films")
    private Set<ratings> ratings;

    @OneToMany(mappedBy="films")
    private Set<comments> comments;

    public films(long id,
                 String name,
                 String actor,
                 String producer,
                 String director,
                 int duration,
                 String description,
                 String country,
                 Timestamp createdAt,
                 Timestamp upadtedAt) {
        this.id = id;
        this.name = name;
        this.actor = actor;
        this.producer = producer;
        this.director = director;
        this.duration = duration;
        this.description = description;
        this.country = country;
        this.createdAt = createdAt;
        this.upadtedAt = upadtedAt;
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

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpadtedAt() {
        return upadtedAt;
    }

    public void setUpadtedAt(Timestamp upadtedAt) {
        this.upadtedAt = upadtedAt;
    }

    @Override
    public String toString() {
        return "films{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", actor='" + actor + '\'' +
                ", producer='" + producer + '\'' +
                ", director='" + director + '\'' +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", country='" + country + '\'' +
                ", createdAt=" + createdAt +
                ", upadtedAt=" + upadtedAt +
                '}';
    }
}
