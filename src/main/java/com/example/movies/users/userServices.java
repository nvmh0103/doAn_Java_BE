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

    @Bean
    public static PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }


}
