package com.cinema.database;

import com.cinema.model.Place;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaceDatabase {
    Connection con = ConnectionProvider.getConnection();
    public PlaceDatabase(Connection con) {
        this.con = con;
    }
    public PlaceDatabase() {}
    public List<Place> selectAllPlaces() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Place> places = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection con = ConnectionProvider.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("select * from places where avail=1 group by id")) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String row = rs.getString("roww");
                String column = rs.getString("columnn");
                boolean available = rs.getBoolean("avail");
                places.add(new Place(id, row, column, available));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return places;
    }

    public Place selectPlace(String row, String column) {
        Place place = null;
        // Step 1: Establishing a Connection
        try (Connection con = ConnectionProvider.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("select id,roww,columnn,avail from places where roww =? and columnn=?")) {
            preparedStatement.setString(1, row);
            preparedStatement.setString(2, column);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                boolean avail = rs.getBoolean("avail");
                place = new Place(id, row, column, avail);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return place;
    }
    public void makePlaceTaken(Place place) throws SQLException {
        try (Connection con = ConnectionProvider.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("update places set avail=0 where roww=? and columnn=?")) {
            preparedStatement.setString(1, place.getRow());
            preparedStatement.setString(2, place.getColumn());
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);

        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public List<String> selectRows() {
        List<String> rows = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection con = ConnectionProvider.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("select distinct roww from places")) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String row = rs.getString("roww");
                rows.add(row);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    public List<String> selectColumns() {
        List<String> rows = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection con = ConnectionProvider.getConnection(); PreparedStatement preparedStatement = con.prepareStatement("select distinct columnn from places")) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String column = rs.getString("columnn");
                rows.add(column);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
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
