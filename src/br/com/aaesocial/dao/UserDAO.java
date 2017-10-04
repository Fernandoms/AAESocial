package br.com.aaesocial.dao;

import br.com.aaesocial.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private static final String TAG = UserDAO.class.getSimpleName();

    private Connection connection;

    public UserDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public void insertUser(User user) {
        String sql = "insert into user(email, password, firstName, lastName, birthDate, photoUrl) values" +
                "(?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setDate(5, Date.valueOf(user.getBirthDate()));
            statement.setString(6, user.getPhotoUrl());

            statement.execute();
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(TAG).log(Level.SEVERE, null, ex);
        }
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setEmail(rs.getString("email"));
                user.setEmail(rs.getString("firstName"));
                user.setEmail(rs.getString("lastName"));
                user.setEmail(rs.getString("photoUrl"));
                user.setBirthDate(rs.getDate("birthDate").toLocalDate());
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TAG).log(Level.SEVERE, null, ex);
        }
        return users;
    }
}
