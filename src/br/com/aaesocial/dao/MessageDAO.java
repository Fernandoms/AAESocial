package br.com.aaesocial.dao;

import br.com.aaesocial.model.FactoryUser;
import br.com.aaesocial.model.Message;
import br.com.aaesocial.model.PersonalUser;
import br.com.aaesocial.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageDAO extends BaseDAO {
    private static final String TAG = MessageDAO.class.getSimpleName();

    public MessageDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public void insertMessage(Message message) {
        String sql = "insert into message(sender, receiver, content, sendDate) values" +
                "(?, ?, ?, NOW())";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, message.getSender().getId());
            statement.setInt(2, message.getReceiver().getId());
            statement.setString(3, message.getContent());

            statement.execute();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(TAG).log(Level.SEVERE, null, ex);
        }
    }

    public List<Message> getReceivedMessages(User user) {
        List<Message> messages = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM message m JOIN user u ON m.sender = u.id WHERE receiver=?");
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                User sender = instanceUser(rs);
                messages.add(instanceMessage(rs, user, sender));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TAG).log(Level.SEVERE, null, ex);
        }
        return messages;
    }


    private Message instanceMessage(ResultSet rs, User user, User sender) {
        Message message = new Message();
        message.setReceiver(user);
        message.setSender(sender);
        try {
            message.setContent(rs.getString("content"));
            message.setSendDate(rs.getDate("sendDate").toLocalDate());
        } catch (SQLException ex) {
            Logger.getLogger(TAG).log(Level.SEVERE, null, ex);
        }
        return message;
    }
}
