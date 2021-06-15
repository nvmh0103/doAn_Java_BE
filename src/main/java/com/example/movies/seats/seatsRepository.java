package com.example.movies.seats;

import com.example.movies.rooms.rooms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface seatsRepository extends JpaRepository<seats,Integer> {
    List<seats> findByRooms(rooms rooms);
    seats findById(long id);
}
