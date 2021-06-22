package com.example.movies.users;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ResponseStatus;

public class userServices {
    public static class response{

    }

    public static class okResponse extends response{
        private String message;

        public okResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
    public static class badResponse extends response{
        private String error;

        public badResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
    public static class tokenResponse extends response{
        private String token;
        private int isAdmin;

        public tokenResponse(String token,int isAdmin) {
            this.token = token;
            this.isAdmin=isAdmin;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getIsAdmin() {
            return isAdmin;
        }

        public void setIsAdmin(int isAdmin) {
            this.isAdmin = isAdmin;
        }

    }

    public static class ticketDetails{
        private String filmsName;
        private String dateTime;
        private String seats;
        private String rooms;
        private int price;

        public ticketDetails(){

        }
        public ticketDetails(String filmsName, String dateTime, String seats, String rooms, int price) {
            this.filmsName = filmsName;
            this.dateTime = dateTime;
            this.seats = seats;
            this.rooms = rooms;
            this.price = price;
        }

        public String getFilmsName() {
            return filmsName;
        }

        public void setFilmsName(String filmsName) {
            this.filmsName = filmsName;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public String getSeats() {
            return seats;
        }

        public void setSeats(String seats) {
            this.seats = seats;
        }

        public String getRooms() {
            return rooms;
        }

        public void setRooms(String rooms) {
            this.rooms = rooms;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
    public static class changeUser{
        private String fullName;
        private String email;
        private String password;
        private String phone;
        private String userName;

        public changeUser(){

        }
        public changeUser(String fullName, String email, String password, String phone, String userName) {
            this.fullName = fullName;
            this.email = email;
            this.password = password;
            this.phone = phone;
            this.userName = userName;
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

        @Override
        public String toString() {
            return "changeUser{" +
                    "fullName='" + fullName + '\'' +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", phone='" + phone + '\'' +
                    ", userName='" + userName + '\'' +
                    '}';
        }
    }
    @Bean
    public static PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }


}
