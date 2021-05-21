package com.example.movies.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class usersController {
    @Autowired
    private usersRepository UsersRepository;
    private PasswordEncoder passwordEncoder=userServices.encoder();




    @PostMapping(path="/add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public userServices.response addNewUser(@RequestBody users user){
        users newUser= user;
        if (UsersRepository.findByEmail(newUser.getEmail()) != null){
            return new userServices.badResponse("Email has been taken!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UsersRepository.save(newUser);
        return new userServices.okResponse("success!");
    }

    @GetMapping(path="/get")
    @ResponseBody
    public Iterable<users> getAllUsers() {
        return UsersRepository.findAll();
    }

    @PostMapping(path="/login")
    @ResponseBody
    public userServices.response login(@RequestBody users user){
        // catch exception here...
        users newUser= UsersRepository.findByEmail(user.getEmail());

        if (passwordEncoder.matches(user.getPassword(),newUser.getPassword())){
            return new userServices.okResponse("Login success!");
        }
        return new userServices.badResponse("Login failed!");
    }
}
