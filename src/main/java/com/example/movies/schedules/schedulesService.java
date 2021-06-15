package com.example.movies.schedules;

import com.example.movies.films.filmsServices;

public class schedulesService {
    public static class response{

    }
    public static class okResponse extends filmsServices.response {
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
    public static class badResponse extends filmsServices.response {
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
    public static class addSchedule {
        private schedules schedule;
        private int film;
        private int rooms;

        public addSchedule(){

        }
        public addSchedule(schedules schedule, int film, int rooms) {
            this.schedule = schedule;
            this.film = film;
            this.rooms = rooms;
        }

        public schedules getSchedule() {
            return schedule;
        }

        public void setSchedule(schedules schedule) {
            this.schedule = schedule;
        }

        public int getFilm() {
            return film;
        }

        public void setFilm(int film) {
            this.film = film;
        }

        public int getRooms() {
            return rooms;
        }

        public void setRooms(int rooms) {
            this.rooms = rooms;
        }

        @Override
        public String toString() {
            return "addSchedule{" +
                    "schedule=" + schedule +
                    ", film=" + film +
                    ", rooms=" + rooms +
                    '}';
        }
    }
}
