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

public class MessageDAO {
    private static final String TAG = MessageDAO.class.getSimpleName();

    private Connection connection;

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
                // Sender
                FactoryUser factoryUser = new FactoryUser();
                User sender = factoryUser.getUser(rs.getBoolean("corporative"));

                sender.setId(rs.getInt("id"));
                sender.setEmail(rs.getString("email"));
                sender.setFirstName(rs.getString("firstName"));
                sender.setLastName(rs.getString("lastName"));
                sender.setPhotoUrl(rs.getString("photoUrl"));
                sender.setBirthDate(rs.getDate("birthDate").toLocalDate());

                Message message = new Message();
                message.setReceiver(user);
                message.setSender(sender);
                message.setContent(rs.getString("content"));
                message.setSendDate(rs.getDate("sendDate").toLocalDate());

                messages.add(message);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TAG).log(Level.SEVERE, null, ex);
        }
        return messages;
    }

}
