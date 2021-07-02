package com.example.movies.films;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface filmsRepository extends JpaRepository<films,Integer> {
    films findById(long id);
    films findByName(String name);
}
