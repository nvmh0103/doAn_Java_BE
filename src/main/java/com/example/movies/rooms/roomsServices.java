package com.example.movies.rooms;


import com.example.movies.seats.seats;

import java.util.List;

public class roomsServices {
    public static class response{

    }
    public static class okResponse extends response {
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
    public static class badResponse extends response {
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
    public static class roomsName{
        private String name;

        public roomsName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
        public roomsName(){

        }

        public void setName(String name) {
            this.name = name;
        }
    }
    public static class roomsCreate{
        private rooms rooms;
        private List<com.example.movies.seats.seats> seats;

        public roomsCreate(){

        }
        public roomsCreate(com.example.movies.rooms.rooms rooms, List<com.example.movies.seats.seats> seats) {
            this.rooms = rooms;
            this.seats = seats;
        }

        public com.example.movies.rooms.rooms getRooms() {
            return rooms;
        }

        public void setRooms(com.example.movies.rooms.rooms rooms) {
            this.rooms = rooms;
        }

        public List<com.example.movies.seats.seats> getSeats() {
            return seats;
        }

        public void setSeats(List<com.example.movies.seats.seats> seats) {
            this.seats = seats;
        }
    }
}
