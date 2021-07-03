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
    public static class deleteFilm{
        private long id;
        deleteFilm(){

        }

        public deleteFilm(long id) {
            this.id = id;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }
    public static class changeFilm{
        private long id;
        private String name;
        private String actor;
        private String director;
        private String description;
        private String country;
        private String pictureLink;

        public changeFilm(){

        }

        public changeFilm(long id, String name, String actor, String director, String description, String country, String pictureLink) {
            this.id = id;
            this.name = name;
            this.actor = actor;
            this.director = director;
            this.description = description;
            this.country = country;
            this.pictureLink = pictureLink;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getActor() {
            return actor;
        }

        public void setActor(String actor) {
            this.actor = actor;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getPictureLink() {
            return pictureLink;
        }

        public void setPictureLink(String pictureLink) {
            this.pictureLink = pictureLink;
        }
    }
    public static class totalPage{
        private int totalPage;
        public totalPage(){

        }

        public totalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }
    }
}
