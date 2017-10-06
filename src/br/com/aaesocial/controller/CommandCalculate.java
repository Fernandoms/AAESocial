package br.com.aaesocial.controller;

import br.com.aaesocial.dao.MessageDAO;
import br.com.aaesocial.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandCalculate implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");

        MessageDAO messageDao = new MessageDAO();
        int messageNumber = messageDao.getReceivedMessages(sessionUser).size();

        sessionUser.setValueToPay(messageNumber);

        request.setAttribute("valueToPay", sessionUser.getValueToPay());
        request.getRequestDispatcher("FrontController?action=Profile").forward(request, response);
    }
}
