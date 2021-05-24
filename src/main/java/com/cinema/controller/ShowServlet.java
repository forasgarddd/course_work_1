package com.cinema.controller;

import com.cinema.database.PlaceDatabase;
import com.cinema.database.ShowDatabase;
import com.cinema.model.Place;
import com.cinema.model.Show;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.sql.*;

@WebServlet("/")
public class ShowServlet extends HttpServlet {
    private ShowDatabase showDatabase;
    private PlaceDatabase placeDatabase;

    public void init() {
        showDatabase = new ShowDatabase();
        placeDatabase = new PlaceDatabase();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/delete":
                    deleteShow(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/choose":
                    listShow(request, response);
                    break;
                case "/list":
                    listShow(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void listShow(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();

        List <Show> listShow = showDatabase.selectAllShows();
        List <Place> listPlace = placeDatabase.selectAllPlaces();
        List <String> listRows = placeDatabase.selectRows();
        List <String> listColumns = placeDatabase.selectColumns();
        request.setAttribute("listShow", listShow);
        request.setAttribute("listPlace", listPlace);
        request.setAttribute("listRows", listRows);
        request.setAttribute("listColumns", listColumns);
        if("admin@gmail.com".equals(session.getAttribute("email"))) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("show-list.jsp");
            dispatcher.forward(request, response);
            } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("show-choose.jsp");
            dispatcher.forward(request, response);
        }
        System.out.println((String) session.getAttribute("email"));

    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("show-form.jsp");
        dispatcher.forward(request, response);
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Show existingShow = showDatabase.selectShow(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("show-form.jsp");
        request.setAttribute("show", existingShow);
        dispatcher.forward(request, response);

    }
    private void insertShow(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String movie = request.getParameter("movie");
        String showtime = request.getParameter("showtime");
        String price = request.getParameter("price");
        Show newShow = new Show(movie, showtime, price);
        showDatabase.insertShow(newShow);
        response.sendRedirect("list");
    }
    private void updateShow(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String movie = request.getParameter("movie");
        String showtime = request.getParameter("showtime");
        String price = request.getParameter("price");

        Show book = new Show(id, movie, showtime, price);
        showDatabase.updateShow(book);
        response.sendRedirect("list");
    }
    private void deleteShow(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        showDatabase.deleteShow(id);
        response.sendRedirect("list");

    }
    private void chooseSeat(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        try {
            PrintWriter out = response.getWriter();
            String movie = request.getParameter("movie");
            String showtime = request.getParameter("showtime");
            String price = request.getParameter("price");
            String placeRow = request.getParameter("row");
            String placeColumn = request.getParameter("column");


            request.setAttribute("selectedMovie", movie);
            request.setAttribute("selectedShowtime", showtime);
            request.setAttribute("selectedPrice", price);
            request.setAttribute("selectedPlaceRow", placeRow);
            request.setAttribute("selectedPlaceColumn", placeColumn);


            Place chosenPlace = placeDatabase.selectPlace(placeRow, placeColumn);
            placeDatabase.makePlaceTaken(chosenPlace);
            System.out.println(chosenPlace);

            System.out.println(request.getParameter("movie"));
            System.out.println(request.getParameter("showtime"));
            System.out.println(request.getParameter("price"));
            System.out.println(request.getParameter("row"));
            System.out.println(request.getParameter("column"));

            if(chosenPlace.isAvailable()) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("ticket.jsp");
                dispatcher.forward(request, response);
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('The place you chose is already taken, please choose another one!');");
                out.println("location='list';");
                out.println("</script>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void generateTicket() {

    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/insert":
                    insertShow(request, response);
                    break;
                case "/update":
                    updateShow(request, response);
                    break;
                case "/seat":
                    chooseSeat(request, response);

            }


        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
