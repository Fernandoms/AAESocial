package br.com.aaesocial.controller;

import br.com.aaesocial.memento.ProfileLayoutMemento;
import br.com.aaesocial.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class CommandPreviousLayout implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");
        Integer index = (Integer) session.getAttribute("stateIndex");
        ArrayList<ProfileLayoutMemento> states =
                (ArrayList<ProfileLayoutMemento>) session.getAttribute("states");

        if (states != null && index >= 0) {
            sessionUser.restoreLayoutFromMemento(states.get(index));
            session.setAttribute("stateIndex", index-1);
        }

        response.sendRedirect(request.getContextPath() + "/FrontController?action=Profile");
    }
}
