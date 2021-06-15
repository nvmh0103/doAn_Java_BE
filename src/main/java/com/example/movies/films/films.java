package com.example.movies.films;

import com.example.movies.schedules.schedules;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
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


    @OneToMany
    private List<schedules> listSchedules= new ArrayList<>();

    public films(){

    }

    public films(long id, String name, String actor, String director, int duration, String description, String country, String pictureLink, List<schedules> listSchedules) {
        this.id = id;
        this.name = name;
        this.actor = actor;
        this.director = director;
        this.duration = duration;
        this.description = description;
        this.country = country;
        this.pictureLink = pictureLink;
        this.listSchedules = listSchedules;
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

    public List<schedules> getListSchedules() {
        return listSchedules;
    }

    public void setListSchedules(List<schedules> listSchedules) {
        this.listSchedules = listSchedules;
    }
}
