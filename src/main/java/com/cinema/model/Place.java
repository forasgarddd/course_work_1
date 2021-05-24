package com.cinema.model;

public class Place {

    int id;
    String row;
    String column;
    boolean available;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Place(int id, String row, String column, boolean available) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.available = available;
    }

    public Place() {
    }

    public Place(String row, String column, boolean available) {
        this.row = row;
        this.column = column;
        this.available = available;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", row='" + row + '\'' +
                ", column='" + column + '\'' +
                ", available=" + available +
                '}';
    }
}
