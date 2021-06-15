package com.example.movies.rooms;

import org.springframework.data.jpa.repository.JpaRepository;

public interface roomsRepository extends JpaRepository<rooms,Integer> {
    rooms findByName(String name);
    rooms findById(long id);
}
