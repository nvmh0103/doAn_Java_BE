package com.example.movies.schedules;

import com.example.movies.bookedSeat.bookedSeat;
import com.example.movies.films.filmsRepository;
import com.example.movies.films.films;
import com.example.movies.rooms.rooms;
import com.example.movies.rooms.roomsRepository;
import com.example.movies.seats.seats;
import com.example.movies.tickets.tickets;
import com.example.movies.tickets.ticketsRepository;
import com.example.movies.users.usersRepository;
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

    @Autowired
    private usersRepository UsersRepository;

    @Autowired
    private ticketsRepository TicketsRepository;

    @PostMapping(path="/schedules/create")
    @ResponseBody
    public ResponseEntity<schedulesService.response> createSchedule(@RequestBody schedulesService.addSchedule Schedules,@RequestAttribute("email") String email){
        if (UsersRepository.findByEmail(email).getAdmin()==0){
            return new ResponseEntity<schedulesService.response>(new schedulesService.badResponse("Not authorized!"),HttpStatus.FORBIDDEN);
        }
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
    public ResponseEntity<Iterable<String>> getAllBookedSeat(@RequestParam long schedules_id){
        schedules Schedule=SchedulesRepository.findById(schedules_id);
        if (Schedule==null){
            return new ResponseEntity<Iterable<String>>(new ArrayList<String>(), HttpStatus.BAD_REQUEST);
        }
        List<bookedSeat> ScheduleBookedSeat=Schedule.getBookedSeats();
        List<String> bookedSeatName=new ArrayList<>();
        for (bookedSeat BookedSeat : ScheduleBookedSeat){
            if (BookedSeat.getSeats() != null){
                bookedSeatName.add(BookedSeat.getSeats().getName());
            }
        }
            return new ResponseEntity<Iterable<String>>(bookedSeatName,HttpStatus.OK);
    }

    @DeleteMapping(path="/schedules/delete")
    @ResponseBody
    public ResponseEntity<schedulesService.response> delete(@RequestParam long schedules_id,@RequestAttribute("email") String email){
        if (UsersRepository.findByEmail(email).getAdmin()==0){
            return new ResponseEntity<schedulesService.response>(new schedulesService.badResponse("Not authorized!"),HttpStatus.FORBIDDEN);
        }
        schedules Schedules= SchedulesRepository.findById(schedules_id);
        films Films=Schedules.getFilm();
        Films.getListSchedules().remove(Schedules);
        FilmsRepository.save(Films);

        rooms Rooms=Schedules.getRooms();
        Rooms.getSchedule().remove(Schedules);
        RoomsRepository.save(Rooms);
        Schedules.getRooms().getSchedule().remove(Schedules);

        if (Schedules==null){
            return new ResponseEntity<schedulesService.response>(new schedulesService.badResponse("Not found schedules"),HttpStatus.BAD_REQUEST);
        }
        SchedulesRepository.delete(Schedules);
        return new ResponseEntity<schedulesService.response>(new schedulesService.okResponse("Deleted!"),HttpStatus.OK);
    }

    @PatchMapping(path="/schedules/changeSchedules")
    @ResponseBody
    public ResponseEntity<schedulesService.response> changeSchedule(@RequestParam long schedules_id,@RequestBody schedulesService.addSchedule Schedule,@RequestAttribute("email") String email){
        if (UsersRepository.findByEmail(email).getAdmin()==0){
            return new ResponseEntity<schedulesService.response>(new schedulesService.badResponse("Not authorized!"),HttpStatus.FORBIDDEN );
        }
        schedules newSchedules=SchedulesRepository.findById(schedules_id);
        if (Schedule.getSchedule().getName()!=null){
            newSchedules.setName(Schedule.getSchedule().getName());
        }
        if (Schedule.getSchedule().getStartAt()!=null){
            newSchedules.setStartAt(Schedule.getSchedule().getStartAt());
        }
        if (Schedule.getRooms()!=0){
            rooms Rooms=newSchedules.getRooms();
            Rooms.getSchedule().remove(newSchedules);
            RoomsRepository.save(Rooms);

            rooms newRoom=RoomsRepository.findById(Schedule.getRooms());
            newSchedules.setRooms(newRoom);
            newRoom.getSchedule().add(newSchedules);
            RoomsRepository.save(newRoom);
        }
        if (Schedule.getFilm()!=0){
            films Films=newSchedules.getFilm();
            Films.getListSchedules().remove(newSchedules);
            FilmsRepository.save(Films);

            films newFilm=FilmsRepository.findById(Schedule.getFilm());
            newSchedules.setFilm(FilmsRepository.findById(Schedule.getFilm()));
            newFilm.getListSchedules().add(newSchedules);
            FilmsRepository.save(newFilm);
        }
        if (Schedule.getSchedule().getEndAt()!=null){
            newSchedules.setEndAt(Schedule.getSchedule().getEndAt());
        }

        SchedulesRepository.save(newSchedules);
        return new ResponseEntity<schedulesService.response>(new schedulesService.okResponse("Success!"),HttpStatus.OK);

    }

}
