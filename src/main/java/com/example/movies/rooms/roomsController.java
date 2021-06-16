package com.example.movies.rooms;

import com.example.movies.seats.seats;
import com.example.movies.seats.seatsRepository;
import com.example.movies.users.users;
import com.example.movies.users.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class roomsController {
    @Autowired
    private roomsRepository RoomsRepository;

    @Autowired
    private usersRepository UsersRepository;

    @Autowired
    private seatsRepository SeatsRepository;

    @PostMapping(path="/rooms/create")
    @ResponseBody
    public ResponseEntity<roomsServices.response> createRooms(@RequestBody rooms rooms,@RequestAttribute("email") String email){
        users users= UsersRepository.findByEmail(email);
        if (users.getAdmin() == 0) {
            return new ResponseEntity<roomsServices.response>(new roomsServices.badResponse("Not authorized!"), HttpStatus.FORBIDDEN);
        }
        rooms newRooms=rooms;
        if (RoomsRepository.findByName(rooms.getName())!=null){
            return new ResponseEntity<roomsServices.response>(new roomsServices.badResponse("Room has existed!"),HttpStatus.BAD_REQUEST);
        }

        for(seats seats : rooms.getSeats()){
            seats.setRooms(newRooms);
        }
        RoomsRepository.save(newRooms);
        return new ResponseEntity<roomsServices.response>(new roomsServices.okResponse("Created!"),HttpStatus.CREATED);
    }
    @GetMapping(path="/rooms/getAllSeat")
    @ResponseBody
    public ResponseEntity<Iterable<seats>> getAllSeats(@RequestBody roomsServices.roomsName roomName){
        rooms newRoom=RoomsRepository.findByName(roomName.getName());
        if (newRoom!=null){
            return new ResponseEntity<Iterable<seats>>(SeatsRepository.findByRooms(newRoom),HttpStatus.OK);
        }
        return new ResponseEntity<Iterable<seats>>(new ArrayList<seats>(),HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path="/rooms/getAllRoom")
    @ResponseBody
    public Iterable<rooms> getAllRooms(){
        return RoomsRepository.findAll();
    }
}
