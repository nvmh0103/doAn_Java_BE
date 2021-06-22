package com.example.movies.users;



import com.example.movies.bookings_detail.booking_details;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class users {
    @Id @GeneratedValue
    private long id;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String userName;
    private int admin;
    // relationship

    @OneToMany(mappedBy = "users")
    private List<booking_details> booking_details=new ArrayList<>();

    public users(){

    }

    public users(long id, String fullName, String email, String password, String phone, String userName, int admin) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userName = userName;
        this.admin = admin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public List<com.example.movies.bookings_detail.booking_details> getBooking_details() {
        return booking_details;
    }

    public void setBooking_details(List<com.example.movies.bookings_detail.booking_details> booking_details) {
        this.booking_details = booking_details;
    }

    @Override
    public String toString() {
        return "users{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", userName='" + userName + '\'' +
                ", admin=" + admin +
                ", booking_details=" + booking_details +
                '}';
    }
}
