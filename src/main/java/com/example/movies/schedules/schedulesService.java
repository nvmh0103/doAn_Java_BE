package com.example.movies.schedules;

import com.example.movies.films.filmsServices;

public class schedulesService {
    public static class response{

    }
    public static class okResponse extends schedulesService.response {
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
    public static class badResponse extends schedulesService.response {
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
        private int films;
        private int rooms;

        public addSchedule(){

        }
        public addSchedule(schedules schedule, int films, int rooms) {
            this.schedule = schedule;
            this.films = films;
            this.rooms = rooms;
        }

        public schedules getSchedule() {
            return schedule;
        }

        public void setSchedule(schedules schedule) {
            this.schedule = schedule;
        }

        public int getFilm() {
            return films;
        }

        public void setFilm(int film) {
            this.films = film;
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
                    ", film=" + films +
                    ", rooms=" + rooms +
                    '}';
        }
    }
    public static class getAllBookedSeat{
        private int schedules_id;

        public getAllBookedSeat(){

        }
        public getAllBookedSeat(int schedules_id) {
            this.schedules_id = schedules_id;
        }

        public int getSchedules_id() {
            return schedules_id;
        }

        public void setSchedules_id(int schedules_id) {
            this.schedules_id = schedules_id;
        }
    }
}
