package com.cinema.controller;

import com.cinema.database.ConnectionProvider;
import com.cinema.database.UserDatabase;
import com.cinema.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", value = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
//make user object
        User userModel = new User(name, email, password);

//create a database model
        UserDatabase regUser = new UserDatabase(ConnectionProvider.getConnection());
        if (regUser.saveUser(userModel)) {
            response.sendRedirect("index.jsp");
        } else {
            String errorMessage = "User Available";
            HttpSession regSession = request.getSession();
            regSession.setAttribute("RegError", errorMessage);
            response.sendRedirect("register.jsp");
        }
    }
}
