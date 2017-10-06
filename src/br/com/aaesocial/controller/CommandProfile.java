package br.com.aaesocial.controller;

import br.com.aaesocial.model.Message;
import br.com.aaesocial.model.User;
import br.com.aaesocial.dao.MessageDAO;
import br.com.aaesocial.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CommandProfile implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MessageDAO messageDao = new MessageDAO();
        UserDAO userDao = new UserDAO();
        User sessionUser = (User) session.getAttribute("user");
        User user;

        sessionUser.accountActivity();

        String idString = request.getParameter("id");
        if (idString != null) {
            user = userDao.getUser(Integer.parseInt(idString));
        } else {
            user = sessionUser;
        }
        List<Message> messages = messageDao.getReceivedMessages(user);
        List<User> receivers = userDao.findAllUsers(user);

        request.setAttribute("user", user);
        request.setAttribute("messages", messages);
        request.setAttribute("receivers", receivers);
        request.setAttribute("myprofile", user.getId() == sessionUser.getId());
        request.setAttribute("notifications", user.getNotifications());
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }
}
