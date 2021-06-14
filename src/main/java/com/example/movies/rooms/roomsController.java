package com.example.movies.rooms;

import com.example.movies.users.users;
import com.example.movies.users.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class roomsController {
    @Autowired
    private roomsRepository RoomsRepository;

    @Autowired
    private usersRepository UsersRepository;

    @PostMapping(path="/rooms/create")
    @ResponseBody
    public ResponseEntity<roomsServices.response> createRooms(@RequestBody rooms rooms,@RequestAttribute("email") String email){
        users users= UsersRepository.findByEmail(email);
        if (users.getAdmin() == 0){
            return new ResponseEntity<roomsServices.response>(new roomsServices.badResponse("Not authorize!"), HttpStatus.FORBIDDEN);
        }
        RoomsRepository.save(rooms);
        return new ResponseEntity<roomsServices.response>(new roomsServices.okResponse("Created!"),HttpStatus.CREATED);
    }
}
