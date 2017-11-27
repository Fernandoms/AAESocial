package br.com.aaesocial.controller;

import br.com.aaesocial.dao.UserDAO;
import br.com.aaesocial.model.PersonalUser;
import br.com.aaesocial.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandLogin implements Command {

    HttpServletRequest request;
    HttpServletResponse response;

    @Override
    public void execute(HttpServletRequest requestServlet, HttpServletResponse responseServlet) throws ServletException, IOException {
        this.request = requestServlet;
        this.response = responseServlet;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login == null || password == null) {
            showLoginPage(request, response);
        } else {
            getLoggedUser(request, response, login, password);
        }
    }

    private void getLoggedUser(String login, String password) {
        UserDAO dao = new UserDAO();
        User user = dao.getUser(login, password);
        if (user == null) {
            showLoginPage(request, response);
        } else {
            defineSession(request, user);
            response.sendRedirect(request.getContextPath() + "/FrontController?action=Profile");
        }
    }

    private void defineSession(User user) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(600);
    }

    private void showLoginPage() {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }


}
