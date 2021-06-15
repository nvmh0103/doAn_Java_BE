package com.example.movies.schedules;

import org.springframework.data.jpa.repository.JpaRepository;

public interface schedulesRepository extends JpaRepository<schedules,Integer> {
    schedules findById(long id);
}
