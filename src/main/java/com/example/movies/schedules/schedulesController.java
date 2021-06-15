package com.example.movies.schedules;

import com.example.movies.films.filmsRepository;
import com.example.movies.films.films;
import com.example.movies.rooms.rooms;
import com.example.movies.rooms.roomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
        return null;
    }
}
