package br.com.aaesocial.controller;

import br.com.aaesocial.dao.MessageDAO;
import br.com.aaesocial.dao.UserDAO;
import br.com.aaesocial.model.Message;
import br.com.aaesocial.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandSend implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");

        sessionUser.notifyAction();

        String receiverId = request.getParameter("receiver");
        String content = request.getParameter("messageContent");

        UserDAO userDao = new UserDAO();
        MessageDAO messageDAO = new MessageDAO();
        User receiver = userDao.getUser(Integer.parseInt(receiverId));

        Message message = new Message();
        message.setContent(content);
        message.setSender(sessionUser);
        message.setReceiver(receiver);

        messageDAO.insertMessage(message);

        response.sendRedirect(request.getContextPath() + "/FrontController?action=Profile");
    }
}
