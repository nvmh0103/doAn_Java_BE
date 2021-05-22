package com.example.movies.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class usersController {
    @Autowired
    private usersRepository UsersRepository;
    private PasswordEncoder passwordEncoder=userServices.encoder();




    @PostMapping(path="/add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<userServices.response> addNewUser(@RequestBody users user){
        users newUser= user;
        if (UsersRepository.findByEmail(newUser.getEmail()) != null){
            return new ResponseEntity<userServices.response>(new userServices.badResponse("Email has been taken!"), HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UsersRepository.save(newUser);
        return new ResponseEntity<userServices.response>(new userServices.okResponse("Successfully!"),HttpStatus.CREATED);
    }

    @GetMapping(path="/get")
    @ResponseBody
    public Iterable<users> getAllUsers() {
        return UsersRepository.findAll();
    }

    @PostMapping(path="/login")
    @ResponseBody
    public ResponseEntity<userServices.response> login(@RequestBody users user){
        // catch exception here...
        users newUser= UsersRepository.findByEmail(user.getEmail());
        if (newUser == null){
            return new ResponseEntity<userServices.response>(new userServices.badResponse("Email or password is not correct!"),HttpStatus.BAD_REQUEST);
        }
        if (passwordEncoder.matches(user.getPassword(),newUser.getPassword())){
            return new ResponseEntity<userServices.response>(new userServices.okResponse("Login success!"),HttpStatus.OK);
        }

        return new ResponseEntity<userServices.response>(new userServices.badResponse("Login failed!"),HttpStatus.BAD_REQUEST);
    }
}