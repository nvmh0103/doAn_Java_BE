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
        seats newSeats=SeatsRepository.findById(CreateTicket.getSeats_id());
        if (newSchedules==null || newFilms==null || newSeats==null){
            return new ResponseEntity<ticketsServices.response>(new ticketsServices.badResponse("Not valid!"),HttpStatus.BAD_REQUEST);
        }
        // create bookedSeat first
        bookedSeat BookedSeat= new bookedSeat();
        BookedSeat.setSeats(newSeats);
        BookedSeat.setSchedules(newSchedules);
        BookedSeatRepository.save(BookedSeat);

        // add bookedSeat to seats and schedules
        // to seats
        List<bookedSeat> seatsBookedSeat=newSeats.getBookedSeats();
        seatsBookedSeat.add(BookedSeat);
        newSeats.setBookedSeats(seatsBookedSeat);
        SeatsRepository.save(newSeats);

        // to schedules
        List<bookedSeat> schedulesBookedSeat= newSchedules.getBookedSeats();
        schedulesBookedSeat.add(BookedSeat);
        newSchedules.setBookedSeats(schedulesBookedSeat);
        SchedulesRepository.save(newSchedules);


        // save ticket
        // add schedules to ticket
        newTicket.setSchedules(newSchedules);
        TicketsRepository.save(newTicket);

        // add ticket to schedule
        List<tickets> listTickets=newSchedules.getTickets();
        listTickets.add(newTicket);
        newSchedules.setTickets(listTickets);
        SchedulesRepository.save(newSchedules);

        // save booking_detail
        booking_details Booking_details= new booking_details();
        Booking_details.setBookedSeat(BookedSeat);
        Booking_details.setUsers(Users);
        Booking_details.setTickets(newTicket);
        Booking_detailsRepository.save(Booking_details);


        // save bookings_detail for user
        List<booking_details> usersBookingDetails=Users.getBooking_details();
        usersBookingDetails.add(Booking_details);
        Users.setBooking_details(usersBookingDetails);
        UsersRepository.save(Users);

        return new ResponseEntity<ticketsServices.response>(new ticketsServices.okResponse("Created!"),HttpStatus.CREATED);

    }

}
