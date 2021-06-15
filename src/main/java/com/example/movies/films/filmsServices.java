package com.example.movies.films;

public class filmsServices {
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
}
