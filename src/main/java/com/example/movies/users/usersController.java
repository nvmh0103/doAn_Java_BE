package com.example.movies.users;

import com.example.movies.bookings_detail.booking_details;
import com.example.movies.bookings_detail.booking_detailsRepository;
import com.example.movies.jwt.util.JwtUtil;
import com.example.movies.tickets.tickets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    private booking_detailsRepository Booking_detailsRepository;


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
        List<tickets> userTicket=User.getTickets();
        List<userServices.ticketDetails> allTicketDetails= new ArrayList<>();
        tickets prev=userTicket.get(userTicket.size()-1);
        boolean firstOne=true;
        for(tickets Ticket: userTicket){
            if ((prev == Ticket) && !firstOne){
                continue;
            }
            userServices.ticketDetails TicketDetails= new userServices.ticketDetails();
            TicketDetails.setFilmsName(Ticket.getSchedules().getFilm().getName());
            TicketDetails.setDateTime(Ticket.getSchedules().getStartAt().toString());
            TicketDetails.setRooms(Ticket.getSchedules().getRooms().getName());
            TicketDetails.setPrice(Ticket.getPrice());
            List<String> seatsName= new ArrayList<>();
            for(booking_details booking: Booking_detailsRepository.findByTickets(Ticket)){
                seatsName.add(booking.getBookedSeat().getSeats().getName());
            }
            TicketDetails.setSeats(seatsName);
            allTicketDetails.add(TicketDetails);
            prev=Ticket;
            firstOne=false;
        }
        return allTicketDetails;
    }

    @GetMapping(path="/users/getTotalPage")
    @ResponseBody
    public ResponseEntity<userServices.totalPage>getTotalPage(@RequestAttribute("email") String email){
        if (UsersRepository.findByEmail(email).getAdmin() == 0){
            return new ResponseEntity<userServices.totalPage>(new userServices.totalPage(0),HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<userServices.totalPage>(new userServices.totalPage(UsersRepository.findAll(PageRequest.of(0,5)).getTotalPages()),HttpStatus.OK);
    }

    @GetMapping(path="/users/getAllUsers")
    @ResponseBody
    public ResponseEntity<Iterable<users>> getAllUser(@RequestAttribute("email") String email,@RequestParam int page){
        if (UsersRepository.findByEmail(email).getAdmin() == 0){
            return new ResponseEntity<Iterable<users>>(new ArrayList<users>(),HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Iterable<users>>(UsersRepository.findAll(PageRequest.of(page-1,5)).getContent(),HttpStatus.OK);
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

    @DeleteMapping(path="/users/adminDelete")
    @ResponseBody
    public ResponseEntity<userServices.response> adminDeleteUser(@RequestBody userServices.adminDeleteUser deletedUser,@RequestAttribute("email") String email){
        if (UsersRepository.findByEmail(email).getAdmin()!=1){
            return new ResponseEntity<userServices.response>(new userServices.badResponse("Not authorized!"),HttpStatus.FORBIDDEN);
        }
        UsersRepository.delete(UsersRepository.findById(deletedUser.getId()));
        return new ResponseEntity<userServices.response>(new userServices.okResponse("Success!"),HttpStatus.OK);
    }

    @GetMapping(path="/users/getTicket")
    @ResponseBody
    public Iterable<tickets> getAllTicket(@RequestAttribute("email") String email){
        return (UsersRepository.findByEmail(email).getTickets());
    }
}
