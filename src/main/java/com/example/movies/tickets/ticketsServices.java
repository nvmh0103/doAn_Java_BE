package com.example.movies.tickets;

import com.example.movies.films.filmsServices;

public class ticketsServices {
    public static class response{

    }
    public static class okResponse extends ticketsServices.response {
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
    public static class badResponse extends ticketsServices.response {
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
    public static class createTicket{
        private tickets ticket;
        private int schedules_id;
        private int seats_id;
        private int films_id;
        private int rooms_id;

        public createTicket(){

        }

        public createTicket(tickets ticket, int schedules_id, int seats_id, int films_id, int rooms_id) {
            this.ticket = ticket;
            this.schedules_id = schedules_id;
            this.seats_id = seats_id;
            this.films_id = films_id;
            this.rooms_id = rooms_id;
        }

        public tickets getTicket() {
            return ticket;
        }

        public void setTicket(tickets ticket) {
            this.ticket = ticket;
        }

        public int getSchedules_id() {
            return schedules_id;
        }

        public void setSchedules_id(int schedules_id) {
            this.schedules_id = schedules_id;
        }

        public int getSeats_id() {
            return seats_id;
        }

        public void setSeats_id(int seats_id) {
            this.seats_id = seats_id;
        }

        public int getFilms_id() {
            return films_id;
        }

        public void setFilms_id(int films_id) {
            this.films_id = films_id;
        }

        public int getRooms_id() {
            return rooms_id;
        }

        public void setRooms_id(int rooms_id) {
            this.rooms_id = rooms_id;
        }
    }
}
