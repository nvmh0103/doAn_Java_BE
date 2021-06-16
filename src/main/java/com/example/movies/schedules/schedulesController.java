package com.example.movies.schedules;

import com.example.movies.bookedSeat.bookedSeat;
import com.example.movies.films.filmsRepository;
import com.example.movies.films.films;
import com.example.movies.rooms.rooms;
import com.example.movies.rooms.roomsRepository;
import com.example.movies.seats.seats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class schedulesController {
    @Autowired
    private schedulesRepository SchedulesRepository;

    @Autowired
    private roomsRepository RoomsRepository;

    @Autowired
    private filmsRepository FilmsRepository;

    @PostMapping(path="/schedules/create")
    @ResponseBody
    public ResponseEntity<schedulesService.response> createSchedule(@RequestBody schedulesService.addSchedule Schedules){
        films newFilms=FilmsRepository.findById(Schedules.getFilm());
        rooms newRooms=RoomsRepository.findById(Schedules.getRooms());
        if (newFilms==null || newRooms==null){
            return new ResponseEntity<schedulesService.response>(new schedulesService.badResponse("Not valid!"),HttpStatus.BAD_REQUEST);
        }
        schedules newSchedules=Schedules.getSchedule();
        newSchedules.setFilm(newFilms);
        newSchedules.setRooms(newRooms);
        SchedulesRepository.save(newSchedules);
        List<schedules> filmsSchedule=newFilms.getListSchedules();
        filmsSchedule.add(newSchedules);
        newFilms.setListSchedules(filmsSchedule);
        FilmsRepository.save(newFilms);
        List<schedules> roomsSchedule=newRooms.getSchedule();
        roomsSchedule.add(newSchedules);
        newRooms.setSchedule(roomsSchedule);
        RoomsRepository.save(newRooms);
        return new ResponseEntity<schedulesService.response>(new schedulesService.okResponse("Created!"),HttpStatus.CREATED);
    }

    @GetMapping(path="/schedules/getAllBookedSeat")
    @ResponseBody
    public ResponseEntity<Iterable<String>> getAllBookedSeat(@RequestBody schedulesService.getAllBookedSeat schedules){
        schedules Schedule=SchedulesRepository.findById(schedules.getSchedules_id());
        if (Schedule==null){
            return new ResponseEntity<Iterable<String>>(new ArrayList<String>(), HttpStatus.BAD_REQUEST);
        }
        List<bookedSeat> ScheduleBookedSeat=Schedule.getBookedSeats();
        System.out.println(ScheduleBookedSeat.toString());
        List<String> bookedSeatName=new ArrayList<>();
        for (bookedSeat BookedSeat : ScheduleBookedSeat){
            if (BookedSeat.getSeats() != null){
                bookedSeatName.add(BookedSeat.getSeats().getName());
            }
        }
            return new ResponseEntity<Iterable<String>>(bookedSeatName,HttpStatus.OK);
    }

}
