package com.cinema.model;

public class Show {

    int id;
    String movie;
    String showtime;
    String price;

    public Show(String movie, String showtime, String price) {
        this.movie = movie;
        this.showtime = showtime;
        this.price = price;
    }

    public Show() {
    }

    public Show(int id, String movie, String showtime, String price) {
        this.id = id;
        this.movie = movie;
        this.showtime = showtime;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
