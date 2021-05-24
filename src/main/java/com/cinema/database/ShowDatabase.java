package com.cinema.database;

import com.cinema.model.Show;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowDatabase {
    private static final String INSERT_SHOWS_SQL = "insert into shows(movie, showtime, price) values (?, ?, ?);";
    private static final String SELECT_SHOW_BY_ID = "select id,movie,showtime,price from shows where id =?";
    private static final String SELECT_ALL_SHOWS = "select * from shows";
    private static final String DELETE_SHOWS_SQL = "delete from shows where id = ?;";
    private static final String UPDATE_SHOWS_SQL = "update shows set movie = ?,showtime= ?, price =? where id = ?;";

    Connection con = ConnectionProvider.getConnection();
    public ShowDatabase(Connection con) {
        this.con = con;
    }
    public ShowDatabase() {

    }



    public void insertShow(Show show) throws SQLException {
        System.out.println(INSERT_SHOWS_SQL);
        try (Connection con = ConnectionProvider.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(INSERT_SHOWS_SQL)) {
            preparedStatement.setString(1, show.getMovie());
            preparedStatement.setString(2, show.getShowtime());
            preparedStatement.setString(3, show.getPrice());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Show selectShow(int id) {
        Show show = null;
        try (Connection con = ConnectionProvider.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SELECT_SHOW_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String movie = rs.getString("movie");
                String showtime = rs.getString("showtime");
                String price = rs.getString("price");
                show = new Show(id, movie, showtime, price);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return show;
    }


    public List<Show> selectAllShows() {

        List<Show> shows = new ArrayList<>();
        try (Connection con = ConnectionProvider.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_SHOWS)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String movie = rs.getString("movie");
                String showtime = rs.getString("showtime");
                String price = rs.getString("price");
                shows.add(new Show(id, movie, showtime, price));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return shows;
    }

    public boolean updateShow(Show show) throws SQLException {
        boolean rowUpdated;
        try (Connection con = ConnectionProvider.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(UPDATE_SHOWS_SQL)) {
            preparedStatement.setString(1, show.getMovie());
            preparedStatement.setString(2, show.getShowtime());
            preparedStatement.setString(3, show.getPrice());
            preparedStatement.setInt(4, show.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    public boolean deleteShow(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection con = ConnectionProvider.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(DELETE_SHOWS_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
