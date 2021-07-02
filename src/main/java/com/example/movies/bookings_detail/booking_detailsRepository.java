package com.example.movies.bookings_detail;

import com.example.movies.tickets.tickets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface booking_detailsRepository extends JpaRepository<booking_details,Integer> {
    List<booking_details> findByTickets(tickets tickets);
}
