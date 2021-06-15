package com.example.movies.rooms;


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
}
