package com.example.movies.tickets;
import com.example.movies.bookedSeat.bookedSeat;
import com.example.movies.bookedSeat.bookedSeatRepository;
import com.example.movies.bookings_detail.booking_details;
import com.example.movies.bookings_detail.booking_detailsRepository;
import com.example.movies.films.films;
import com.example.movies.films.filmsRepository;
import com.example.movies.films.filmsServices;
import com.example.movies.schedules.schedules;
import com.example.movies.schedules.schedulesRepository;
import com.example.movies.seats.seats;
import com.example.movies.seats.seatsRepository;
import com.example.movies.users.users;
import com.example.movies.users.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ticketsController {
    @Autowired
    private ticketsRepository TicketsRepository;

    @Autowired
    private bookedSeatRepository BookedSeatRepository;

    @Autowired
    private schedulesRepository SchedulesRepository;

    @Autowired
    private booking_detailsRepository Booking_detailsRepository;

    @Autowired
    private usersRepository UsersRepository;

    @Autowired
    private filmsRepository FilmsRepository;

    @Autowired
    private seatsRepository SeatsRepository;

    @PostMapping(path="/tickets/create")
    @ResponseBody
    public ResponseEntity<ticketsServices.response> createTicket(@RequestBody ticketsServices.createTicket CreateTicket, @RequestAttribute("email") String email){
        users Users= UsersRepository.findByEmail(email);
        tickets newTicket=CreateTicket.getTicket();
        schedules newSchedules=SchedulesRepository.findById(CreateTicket.getSchedules_id());
        films newFilms=FilmsRepository.findById(CreateTicket.getFilms_id());
        List<seats> newSeats=new ArrayList<seats>();
        if (newSchedules==null || newFilms==null || newSeats==null){
            return new ResponseEntity<ticketsServices.response>(new ticketsServices.badResponse("Not valid!"),HttpStatus.BAD_REQUEST);
        }


        // save ticket first;
        newTicket.setSchedules(newSchedules);
        newTicket.setUser(Users);
        System.out.println(Users.getTickets().size());
        TicketsRepository.save(newTicket);
        System.out.println(Users.getTickets().size());

        // save ticket for user
        List<tickets> usersTickets=Users.getTickets();
        usersTickets.add(newTicket);
        Users.setTickets(usersTickets);
        UsersRepository.save(Users);
        for (int id : CreateTicket.getSeats_id()){
            newSeats.add(SeatsRepository.findById(id));
        }

        for (seats Seat : newSeats){
            bookedSeat BookedSeat= new bookedSeat();
            BookedSeat.setSeats(Seat);
            BookedSeat.setSchedules(newSchedules);
            BookedSeatRepository.save(BookedSeat);
            System.out.println(Users.getTickets().size());

            // to schedules
            List<bookedSeat> schedulesBookedSeat= newSchedules.getBookedSeats();
            schedulesBookedSeat.add(BookedSeat);
            newSchedules.setBookedSeats(schedulesBookedSeat);
            SchedulesRepository.save(newSchedules);
            System.out.println(Users.getTickets().size());

            // save booking_detail
            booking_details Booking_details= new booking_details();
            Booking_details.setBookedSeat(BookedSeat);
            Booking_details.setTickets(newTicket);
            Booking_detailsRepository.save(Booking_details);
            System.out.println(Users.getTickets().size());
        }
        return new ResponseEntity<ticketsServices.response>(new ticketsServices.okResponse("Created!"),HttpStatus.CREATED);
    }

}
