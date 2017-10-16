package br.com.aaesocial.controller;

import br.com.aaesocial.memento.ProfileLayoutMemento;
import br.com.aaesocial.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class CommandNewLayout implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");
        ArrayList<ProfileLayoutMemento> states =
                (ArrayList<ProfileLayoutMemento>) session.getAttribute("states");

        if (states == null) {
            states = new ArrayList<>();
            session.setAttribute("states", states);
        }

        Random rng = new Random();
        String chars = "0123456789ABCDEF";
        String newColor = "";

        for (int i = 0; i < 6; ++i) {
            newColor += chars.charAt(rng.nextInt(16));
        }

        states.add(sessionUser.saveLayoutToMemento());
        sessionUser.setBgColor(newColor);
        session.setAttribute("stateIndex", states.size()-1);

        response.sendRedirect(request.getContextPath() + "/FrontController?action=Profile");
    }
}
