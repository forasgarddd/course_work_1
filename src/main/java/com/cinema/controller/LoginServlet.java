package com.cinema.controller;

import com.cinema.database.ConnectionProvider;
import com.cinema.database.UserDatabase;
import com.cinema.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String logemail = request.getParameter("email");
        String logpass = request.getParameter("password");

        UserDatabase db =  new UserDatabase(ConnectionProvider.getConnection());
        User user = db.logUser(logemail, logpass);
        if("admin@gmail.com".equals(logemail) && "admin".equals(logpass)) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", user);
            session.setAttribute("email", logemail);
            response.sendRedirect("admin.jsp");
        }
        if(user!=null){
            HttpSession session = request.getSession();
            session.setAttribute("email", logemail);
            session.setAttribute("logUser", user);
            response.sendRedirect("welcome.jsp");
        } else{
            out.println("user not found");
        }

    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
