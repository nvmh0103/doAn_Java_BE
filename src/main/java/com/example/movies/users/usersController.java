package com.example.movies.users;

import com.example.movies.bookings_detail.booking_details;
import com.example.movies.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class usersController {
    @Autowired
    private usersRepository UsersRepository;
    @Autowired
    private JwtUtil jwt;


    private PasswordEncoder passwordEncoder=userServices.encoder();


    @PostMapping(path="/add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<userServices.response> addNewUser(@RequestBody users user){
        users newUser= user;
        if (UsersRepository.findByEmail(newUser.getEmail()) != null){
            return new ResponseEntity<userServices.response>(new userServices.badResponse("Email has been taken!"), HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String token=jwt.generateToken(user.getEmail());
        UsersRepository.save(newUser);

        return new ResponseEntity<userServices.response>(new userServices.tokenResponse(token,newUser.getAdmin()),HttpStatus.CREATED);
    }

    @GetMapping(path="users/get")
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
            String token= jwt.generateToken(newUser.getEmail());
            return new ResponseEntity<userServices.response>(new userServices.tokenResponse(token,newUser.getAdmin()),HttpStatus.OK);
        }

        return new ResponseEntity<userServices.response>(new userServices.badResponse("Email or password is not correct!"),HttpStatus.BAD_REQUEST);
    }
    @GetMapping(path="/getUserProfile")
    public users getUserProfile(@RequestAttribute("email") String email){
        users user= UsersRepository.findByEmail(email);
        return user;
    }

    @GetMapping(path="/users/getBookingDetails")
    @ResponseBody
    public Iterable<userServices.ticketDetails> getAllBookingDetails(@RequestAttribute("email") String email){
        users User=UsersRepository.findByEmail(email);
        List<booking_details> userBookingDetails=User.getBooking_details();
        List<userServices.ticketDetails> allTicketDetails= new ArrayList<>();
        for(booking_details booking_detail : userBookingDetails){
            userServices.ticketDetails TicketDetails= new userServices.ticketDetails();
            TicketDetails.setSeats(booking_detail.getBookedSeat().getSeats().getName());
            TicketDetails.setDateTime(booking_detail.getBookedSeat().getSchedules().getStartAt().toString());
            TicketDetails.setRooms(booking_detail.getBookedSeat().getSeats().getRooms().getName());
            TicketDetails.setFilmsName(booking_detail.getBookedSeat().getSchedules().getFilm().getName());
            TicketDetails.setPrice(booking_detail.getTickets().getPrice());
            allTicketDetails.add(TicketDetails);
        }
        return allTicketDetails;
    }

    @GetMapping(path="/users/getAllUsers")
    @ResponseBody
    public ResponseEntity<Iterable<users>> getAllUser(@RequestAttribute("email") String email){
        if (UsersRepository.findByEmail(email).getAdmin() == 0){
            return new ResponseEntity<Iterable<users>>(new ArrayList<users>(),HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Iterable<users>>(UsersRepository.findAll(),HttpStatus.OK);
    }

    @PatchMapping(path="/users/changeUsers")
    @ResponseBody
    public ResponseEntity<userServices.response> changeUsers(@RequestBody userServices.changeUser changedUser,@RequestAttribute("email") String email){
        if (UsersRepository.findByEmail((email))==null){
            return new ResponseEntity<userServices.response>(new userServices.badResponse("User does not exist!"),HttpStatus.BAD_REQUEST);
        }
        users newUser=UsersRepository.findByEmail(email);
        if (changedUser.getEmail()!= null){
            newUser.setEmail(changedUser.getEmail());
        }
        if (changedUser.getFullName()!= null){
            newUser.setFullName(changedUser.getFullName());
        }
        if (changedUser.getPassword()!= null){
            newUser.setPassword(passwordEncoder.encode(changedUser.getPassword()));
        }
        if (changedUser.getPhone()!= null){
            newUser.setPhone(changedUser.getPhone());
        }
        if (changedUser.getUserName()!= null){
            newUser.setUserName(changedUser.getUserName());
        }

        UsersRepository.save(newUser);
        return new ResponseEntity<userServices.response>(new userServices.okResponse("Success!"),HttpStatus.OK);
    }

    @DeleteMapping(path="/users/delete")
    @ResponseBody
    public ResponseEntity<userServices.response> deleteUser(@RequestAttribute("email") String email){
        users newUser=UsersRepository.findByEmail(email);
        try{
            UsersRepository.delete(newUser);
            return new ResponseEntity<userServices.response>(new userServices.okResponse("Deleted!"),HttpStatus.OK);
        } catch(Exception ex){
            return new ResponseEntity<userServices.response>(new userServices.badResponse("Something wrong happened!"),HttpStatus.BAD_REQUEST);
        }
    }
}
