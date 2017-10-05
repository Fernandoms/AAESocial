package br.com.aaesocial.controller;

import br.com.aaesocial.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandLogin implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // DEVERIA VERIFICAR E PEGAR DO BANCO
            User user = new User();
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(600);

            response.sendRedirect(request.getContextPath() + "/FrontController?action=Profile");
        }
    }
}
