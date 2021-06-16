package com.example.movies.films;


import com.example.movies.rooms.roomsRepository;
import com.example.movies.schedules.schedulesRepository;
import com.example.movies.users.users;
import com.example.movies.users.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class filmsController {

    @Autowired
    private com.example.movies.films.filmsRepository filmsRepository;

    @Autowired
    private schedulesRepository SchedulesRepository;

    @Autowired
    private roomsRepository RoomsRepository;

    @Autowired
    private usersRepository UsersRepository;

    @GetMapping(path="/films/get")
    public Iterable<films> getAllFilms(){
        return filmsRepository.findAll();
    }

    @PostMapping(path="/films/create")
    @ResponseBody
    public ResponseEntity<filmsServices.response> createMovie(@RequestBody films films,@RequestAttribute("email") String email){
        users User=UsersRepository.findByEmail(email);
        if (User.getAdmin()==0){
            return new ResponseEntity<filmsServices.response>(new filmsServices.badResponse("Not authorized!"),HttpStatus.FORBIDDEN);
        }
        if (filmsRepository.findByName(films.getName()) != null){
            return new ResponseEntity<filmsServices.response>(new filmsServices.badResponse("Film has existed!"),HttpStatus.BAD_REQUEST);
        }
        try{
            filmsRepository.save(films);
            return new ResponseEntity<filmsServices.response>(new filmsServices.okResponse("Created!"),HttpStatus.CREATED);
        } catch (Exception ex){
            return new ResponseEntity<filmsServices.response>(new filmsServices.badResponse("Something happened!"),HttpStatus.BAD_REQUEST);
        }
    }



}
